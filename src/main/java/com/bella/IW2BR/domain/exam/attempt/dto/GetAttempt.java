package com.bella.IW2BR.domain.exam.attempt.dto;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.QuestionAnswer;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.dto.GetQuestionAnswer;
import com.bella.IW2BR.domain.exam.exam.Exam;
import java.util.List;

/**
 * DTO representing {@link ExamAttempt} containing only the base info
 *
 * <p>Does not contain the list of questions and answers
 *
 * @param id
 * @param examId
 * @param examTitle
 * @param examDescription
 */
public record GetAttempt(
    Long id,
    Long examId,
    Double score,
    String examTitle,
    String examDescription,
    List<GetQuestionAnswer> questions) {
  /**
   * Maps {@link ExamAttempt} to {@link GetAttempt}
   *
   * @param attempt {@link ExamAttempt}
   * @return {@link GetAttempt}
   */
  public static GetAttempt to(ExamAttempt attempt) {
    Exam exam = attempt.getExam();

    return new GetAttempt(
        attempt.getId(),
        exam.getId(),
        attempt.getScore(),
        exam.getTitle(),
        exam.getDescription(),
        null);
  }

  /**
   * Maps {@link ExamAttempt} to {@link GetAttempt}
   *
   * @param attempt {@link ExamAttempt}
   * @param answers List of {@link Answer}
   * @return {@link GetAttempt}
   */
  public static GetAttempt to(ExamAttempt attempt, List<QuestionAnswer> answers) {
    Exam exam = attempt.getExam();
    List<GetQuestionAnswer> convertedAnswers = answers.stream().map(GetQuestionAnswer::to).toList();
    return new GetAttempt(
        attempt.getId(),
        exam.getId(),
        attempt.getScore(),
        exam.getTitle(),
        exam.getDescription(),
        convertedAnswers);
  }
}
