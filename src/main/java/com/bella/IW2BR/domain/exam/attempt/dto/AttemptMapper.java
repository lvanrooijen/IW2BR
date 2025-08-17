package com.bella.IW2BR.domain.exam.attempt.dto;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.answer.dto.GetAnswer;
import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.QuestionAnswer;
import com.bella.IW2BR.domain.exam.attempt.questionanswer.dto.GetQuestionAnswer;
import com.bella.IW2BR.domain.exam.exam.Exam;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AttemptMapper {
  public GetAttempt toGet(ExamAttempt attempt) {
    Exam exam = attempt.getExam();
    return new GetAttempt(attempt.getId(), exam.getId(), exam.getTitle(), exam.getDescription());
  }

  public GetCompletedAttempt toGetCompleted(ExamAttempt attempt, List<QuestionAnswer> answers) {
    Exam exam = attempt.getExam();
    List<GetQuestionAnswer> convertedAnswers =
        answers.stream().map(this::toGetQuestionAnswer).toList();
    return new GetCompletedAttempt(
        attempt.getId(),
        exam.getId(),
        attempt.getScore(),
        exam.getTitle(),
        exam.getDescription(),
        convertedAnswers);
  }

  public GetQuestionAnswer toGetQuestionAnswer(QuestionAnswer entity) {
    String question = entity.getQuestion().getQuestion();
    List<GetAnswer> answers = entity.getAnswers().stream().map(this::toGetAnswer).toList();

    return new GetQuestionAnswer(question, answers);
  }

  public GetAnswer toGetAnswer(Answer answer) {
    return new GetAnswer(answer.getAnswer(), answer.isCorrect());
  }
}
