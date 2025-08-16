package com.bella.IW2BR.domain.exam.attempt;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.exam.attempt.dto.ExamAttemptMapper;
import com.bella.IW2BR.domain.exam.attempt.dto.GetExamAttempt;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.exceptions.exam.FinalisedExamException;
import com.bella.IW2BR.exceptions.generic.ResourceNotInEnvironmentException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamAttemptService {
  private final ExamAttemptRepository attemptRepository;
  private final ExamAttemptMapper mapper;
  private final EnvironmentHelperMethods helperMethods;

  public GetExamAttempt create(Long environmentId, Long examId) {
    helperMethods.throwIfNotOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(examId);
    Environment environment = helperMethods.getEnvironmentOrThrow(environmentId);

    helperMethods.throwIfNotInEnvironment(exam, environmentId);
    throwIfExamNotFinalised(exam);

    ExamAttempt examAttempt =
        ExamAttempt.builder()
            .environment(environment)
            .startedAt(LocalDateTime.now())
            .exam(exam)
            .build();

    attemptRepository.save(examAttempt);
    return mapper.toGet(examAttempt);
  }
    private void throwIfExamNotFinalised(Exam exam) {
        if (!exam.isFinalised()) {
            throw new FinalisedExamException("Can create an ExamAttempt if exam is not finalised");
        }
    }

    private void throwIfNotInExam(Exam exam, ExamAttempt attempt) {
        helperMethods.throwIfIdMismatch(
                exam.getId(),
                attempt.getExam().getId(),
                new ResourceNotInEnvironmentException(
                        "Exam ID in path variable does not match exam ID connected to this exam attempt"));
    }
}
