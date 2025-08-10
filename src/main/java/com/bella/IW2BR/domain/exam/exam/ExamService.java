package com.bella.IW2BR.domain.exam.exam;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.exam.exam.dto.ExamMapper;
import com.bella.IW2BR.domain.exam.exam.dto.GetExam;
import com.bella.IW2BR.domain.exam.exam.dto.PatchExam;
import com.bella.IW2BR.domain.exam.exam.dto.PostExam;
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
  private final ExamMapper mapper;

  public GetExam create(Long environmentId, PostExam body) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    Environment environment = helperMethods.getEnvironmentOrThrow(environmentId);
    Exam exam = mapper.fromPost(body, environment);

    examRepository.save(exam);
    return mapper.toGet(exam);
  }

  public GetExam getById(Long environmentId, Long id) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(id);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    return mapper.toGet(exam);
  }

  public List<GetExam> getAll(Long environmentId) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    List<Exam> exams = examRepository.findByEnvironmentId(environmentId);

    return exams.stream().map(mapper::toGet).toList();
  }

  public GetExam update(Long environmentId, Long id, PatchExam patch) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(id);
    helperMethods.throwIfExamIsFinalised(exam);

    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    mapper.updateFields(exam, patch);
    examRepository.save(exam);

    return mapper.toGet(exam);
  }

  public void delete(Long environmentId, Long id) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(id);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    if (exam.isFinalised()) {
      // TODO soft delete. en soft delete alles wat gerelateerd is? of wat?
      log.warn("SOFT DELETE NOT IMPLEMENTED YET");
    } else {
      examRepository.deleteById(id);
    }
  }
}
