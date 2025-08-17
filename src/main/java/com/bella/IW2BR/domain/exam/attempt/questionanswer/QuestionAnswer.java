package com.bella.IW2BR.domain.exam.attempt.questionanswer;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;
import com.bella.IW2BR.domain.exam.question.Question;
import jakarta.persistence.*;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "question_answers")
@Entity
@Data
@NoArgsConstructor
public class QuestionAnswer {
  @Builder
  public QuestionAnswer(
      ExamAttempt examAttempt,
      Question question,
      List<Answer> answers,
      int expectedCorrectAnswers,
      int actualCorrectAnswers) {
    this.examAttempt = examAttempt;
    this.question = question;
    this.answers = answers;
    this.expectedCorrectAnswers = expectedCorrectAnswers;
    this.actualCorrectAnswers = actualCorrectAnswers;
  }

  @Id @GeneratedValue private Long id;

  @ManyToOne
  @JoinColumn(name = "exam_attempt_id", nullable = false)
  private ExamAttempt examAttempt;

  @ManyToOne
  @JoinColumn(name = "question_id", nullable = false)
  private Question question;

  @OneToMany
  @JoinTable(
      joinColumns = @JoinColumn(name = "question_answer"),
      inverseJoinColumns = @JoinColumn(name = "answers"))
  private List<Answer> answers;

  /** Represents the amount of correct answers a question is expected to have */
  @Column(name = "expected_correct_answers", nullable = false)
  private int expectedCorrectAnswers;

  @Column(name = "actual_correct_answers", nullable = false)
  private int actualCorrectAnswers;

  public boolean isCorrect() {
    return expectedCorrectAnswers == actualCorrectAnswers;
  }
}
