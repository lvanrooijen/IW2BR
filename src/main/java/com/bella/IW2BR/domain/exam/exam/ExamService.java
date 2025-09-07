package com.bella.IW2BR.domain.exam.exam;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;
import com.bella.IW2BR.domain.exam.exam.dto.GetExam;
import com.bella.IW2BR.domain.exam.exam.dto.PatchExam;
import com.bella.IW2BR.domain.exam.exam.dto.PostExam;
import com.bella.IW2BR.exceptions.exam.InvalidAnswerAmountException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {
  private final EnvironmentHelperMethods helperMethods;
  private final ExamRepository examRepository;

  public GetExam create(Long environmentId, PostExam body) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Environment environment = helperMethods.getEnvironmentOrThrow(environmentId);
    Exam exam = PostExam.from(body, environment);

    examRepository.save(exam);
    return GetExam.to(exam);
  }

  public GetExam getById(Long environmentId, Long id) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(id);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    return GetExam.to(exam);
  }

  public List<GetExam> getAll(Long environmentId) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    List<Exam> exams = examRepository.findByEnvironmentId(environmentId);

    return exams.stream().map(GetExam::to).toList();
  }

  public GetExam update(Long environmentId, Long id, PatchExam patch) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(id);
    helperMethods.throwIfExamIsFinalised(exam);

    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    if (patch.finalise() && exam.getQuestions().size() <= 6) {
      throw new InvalidAnswerAmountException(
          "Exam needs to have at least 6 questions before you finalise");
    }
    PatchExam.patch(exam, patch);
    examRepository.save(exam);

    return GetExam.to(exam);
  }

  /**
   * Deletes an exam
   *
   * <p>finalized exams get soft-deleted because there may be {@link ExamAttempt}'s connected to it.
   *
   * @param environmentId
   * @param id {@link Exam} ID
   */
  public void delete(Long environmentId, Long id) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(id);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    if (exam.isFinalised()) {
      exam.setDeleted(true);
      examRepository.save(exam);
    } else {
      examRepository.deleteById(id);
    }
  }
}
