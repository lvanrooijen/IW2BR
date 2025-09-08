package com.bella.IW2BR.domain.exam.attempt;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.attempt.dto.GetAttempt;
import com.bella.IW2BR.domain.exam.attempt.dto.PostAttempt;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.QuestionAnswer;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.QuestionAnswerRepository;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.dto.PostQuestionAnswer;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.exam.question.Question;
import com.bella.IW2BR.exceptions.exam.ExamAttemptSubmittedException;
import com.bella.IW2BR.exceptions.exam.FinalisedExamException;
import com.bella.IW2BR.exceptions.exam.InvalidAnswerAmountException;
import com.bella.IW2BR.exceptions.generic.ResourceNotConnectedToParentException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ExamAttemptService {
  private final ExamAttemptRepository attemptRepository;
  private final EnvironmentHelperMethods helperMethods;
  private final QuestionAnswerRepository questionAnswerRepository;

  public GetAttempt create(Long environmentId, Long examId) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Exam exam = helperMethods.getExamOrThrow(examId);
    Environment environment = helperMethods.getEnvironmentOrThrow(environmentId);

    helperMethods.throwIfNotInEnvironment(exam, environmentId);
    throwIfExamNotFinalised(exam);

    ExamAttempt examAttempt =
        ExamAttempt.builder()
            .environment(environment)
            .startedAt(LocalDateTime.now())
            .exam(exam)
            .isCompleted(false)
            .build();

    attemptRepository.save(examAttempt);
    return GetAttempt.to(examAttempt);
  }

  public GetAttempt submit(Long environmentId, Long examId, Long attemptId, PostAttempt body) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    Exam exam = helperMethods.getExamOrThrow(examId);
    ExamAttempt attempt = helperMethods.getExamAttemptOrThrow(attemptId);
    throwIfCompleted(attempt);

    checkPathVariablesOrThrow(environmentId, examId, exam, attempt);

    int submittedAnswers = body.questions().size();
    int expectedAnswers = exam.getQuestions().size();
    throwIfInvalidAmountOfAnswersIsSubmitted(submittedAnswers, expectedAnswers);

    List<QuestionAnswer> questionAnswers =
        body.questions().stream()
            .map(questionAnswer -> convertAndSaveQuestionAnswer(questionAnswer, attempt))
            .toList();

    attempt.setScore(calculateScore(expectedAnswers, questionAnswers));
    attempt.setCompletedAt(LocalDateTime.now());
    attempt.setCompleted(true);

    attemptRepository.save(attempt);

    return GetAttempt.to(attempt, questionAnswers);
  }

  public GetAttempt getById(Long environmentId, Long examId, Long attemptId) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    Exam exam = helperMethods.getExamOrThrow(examId);
    ExamAttempt attempt = helperMethods.getExamAttemptOrThrow(attemptId);
    checkPathVariablesOrThrow(environmentId, examId, exam, attempt);

    if (attempt.isCompleted()) {
      List<QuestionAnswer> answers = questionAnswerRepository.findAllByExamAttemptId(attemptId);
      return GetAttempt.to(attempt, answers);
    } else {
      return GetAttempt.to(attempt);
    }
  }

  public List<GetAttempt> getAll(Long environmentId, Long examId, AttemptType type) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    Exam exam = helperMethods.getExamOrThrow(examId);
    helperMethods.throwIfNotInEnvironment(exam, environmentId);

    List<ExamAttempt> attempts;
    if (type == AttemptType.INCOMPLETE) {
      attempts = attemptRepository.findByIsCompleted(false);
    } else if (type == AttemptType.COMPLETE) {
      attempts = attemptRepository.findByIsCompleted(true);
    } else {
      attempts = attemptRepository.findAll();
    }
    return attempts.stream().map(GetAttempt::to).toList();
  }

  public void delete(Long environmentId, Long examId, Long attemptId) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    Exam exam = helperMethods.getExamOrThrow(examId);
    ExamAttempt attempt = helperMethods.getExamAttemptOrThrow(attemptId);
    checkPathVariablesOrThrow(environmentId, examId, exam, attempt);

    List<QuestionAnswer> questionAnswers =
        questionAnswerRepository.findAllByExamAttemptId(attemptId);
    questionAnswerRepository.deleteAll(questionAnswers);

    attemptRepository.deleteById(attemptId);
  }

  // ~~~~~~~~~~~~~~~~~ HELPER METHODS ~~~~~~~~~~~~~~~~~

  private void checkPathVariablesOrThrow(
      Long environmentId, Long examId, Exam exam, ExamAttempt attempt) {
    helperMethods.throwIfNotInEnvironment(exam, environmentId);
    helperMethods.throwIfNotInEnvironment(attempt, environmentId);
    throwIfNotInExam(exam, attempt);

    throwIfExamPathVariableDoesNotMatchExamID(examId, attempt);
  }

  public QuestionAnswer convertAndSaveQuestionAnswer(PostQuestionAnswer body, ExamAttempt attempt) {
    Question question = helperMethods.getQuestionOrThrow(body.id());
    int expectedCorrectAnswers =
        Math.toIntExact(question.getAnswers().stream().filter(Answer::isCorrect).count());

    List<Answer> answers =
        body.selectedAnswers().stream().map(helperMethods::getAnswerOrThrow).toList();
    int actualCorrectAnswers = Math.toIntExact(answers.stream().filter(Answer::isCorrect).count());

    QuestionAnswer questionAnswer =
        QuestionAnswer.builder()
            .examAttempt(attempt)
            .question(question)
            .expectedCorrectAnswers(expectedCorrectAnswers)
            .answers(answers)
            .actualCorrectAnswers(actualCorrectAnswers)
            .build();

    questionAnswerRepository.save(questionAnswer);
    return questionAnswer;
  }

  /**
   * Verifies if exam is finalised. throw {@link FinalisedExamException} if that is not the case.
   *
   * @param exam
   */
  private void throwIfExamNotFinalised(Exam exam) {
    if (!exam.isFinalised()) {
      throw new FinalisedExamException("Can create an ExamAttempt if exam is not finalised");
    }
  }

  private void throwIfNotInExam(Exam exam, ExamAttempt attempt) {
    helperMethods.throwIfIdMismatch(
        exam.getId(),
        attempt.getExam().getId(),
        new ResourceNotConnectedToParentException(
            "Exam ID in path variable does not match exam ID connected to this exam attempt"));
  }

  /**
   * Verifies if an attempt was already completed and throws {@link ExamAttemptSubmittedException}
   * if it is
   *
   * @param attempt {@link ExamAttempt}
   */
  private void throwIfCompleted(ExamAttempt attempt) {
    if (attempt.isCompleted()) {
      throw new ExamAttemptSubmittedException("Exam was already submitted, no second chances :(");
    }
  }

  private void throwIfInvalidAmountOfAnswersIsSubmitted(int submittedAnswers, int expectedAnswers) {
    if (submittedAnswers != expectedAnswers) {
      if (submittedAnswers > expectedAnswers || submittedAnswers == 0) {

        throw new InvalidAnswerAmountException(
            String.format(
                "You have submitted %d answers, but this exams contains %d answers",
                submittedAnswers, expectedAnswers));
      }
    }
  }

  private double calculateScore(int expectedAnswers, List<QuestionAnswer> submittedAnswers) {
    int amountOfCorrectAnswers =
        Math.toIntExact(submittedAnswers.stream().filter(QuestionAnswer::isCorrect).count());

    double score = ((double) amountOfCorrectAnswers / expectedAnswers) * 100;

    return Math.round((score * 100.0) / 100);
  }

  private void throwIfExamPathVariableDoesNotMatchExamID(Long examId, ExamAttempt attempt) {
    helperMethods.throwIfIdMismatch(
        examId,
        attempt.getExam().getId(),
        new ResourceNotConnectedToParentException(
            "Path variable representing Exam id does not match the exam id of this exam attempt id."));
  }
}
