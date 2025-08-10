package com.bella.IW2BR.domain.exam.question.dto;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.answer.dto.PostAnswer;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.exam.question.Question;
import com.bella.IW2BR.domain.tag.Tag;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service that handles mapping between DTOs and the entity
 *
 * <p>Contains methods for mapping on {@link Question} and {@link Answer}
 *
 * <p>Methods:
 *
 * <ul>
 *   <li>Map {@link PostQuestion} DTO to {@link Question} entity
 *   <li>Map {@link PostAnswer} DTO to {@link Answer}
 *   <li>Map {@link Question} entity to {@link GetQuestion}
 * </ul>
 */
@Service
public class QuestionMapper {
  public GetQuestion toGet(Question question) {
    Long tagId = question.getTag() == null ? null : question.getTag().getId();

    return new GetQuestion(
        question.getId(),
        question.getQuestion(),
        question.getAnswerAmount(),
        question.getAnswers(),
        question.getExam().getId(),
        tagId);
  }

  /**
   * Overloaded to allow to manually provide the answers, when creating the question the answers may
   * not be available due to the transaction not being completed
   *
   * @param question
   * @param answers
   * @return {@link GetQuestion}
   */
  public GetQuestion toGet(Question question, List<Answer> answers) {
    Long tagId = question.getTag() == null ? null : question.getTag().getId();

    return new GetQuestion(
        question.getId(),
        question.getQuestion(),
        question.getAnswerAmount(),
        answers,
        question.getExam().getId(),
        tagId);
  }

  public Question fromPost(PostQuestion body, Exam exam) {
    return Question.builder()
        .question(body.question())
        .answerAmount(body.size().getAnswerAmount())
        .exam(exam)
        .build();
  }

  public Question fromPost(PostQuestion body, Exam exam, Tag tag) {
    Question question = fromPost(body, exam);
    question.setTag(tag);
    return question;
  }

  public Answer fromPostAnswer(PostAnswer body, Question question) {
    return Answer.builder()
        .answer(body.answer())
        .isCorrect(body.isCorrect())
        .question(question)
        .build();
  }

  public Question updateQuestionFields(Question question, PatchQuestion patch) {
    if (patch.question() != null) {
      question.setQuestion(patch.question());
    }
    if (patch.size() != null) {
      question.setAnswerAmount(patch.size().getAnswerAmount());
    }
    return question;
  }

  public Question updateQuestionFields(Question question, PatchQuestion patchQuestion, Tag tag) {
    Question updatedQuestion = updateQuestionFields(question, patchQuestion);
    updatedQuestion.setTag(tag);
    return updatedQuestion;
  }
}
