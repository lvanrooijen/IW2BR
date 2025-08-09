package com.bella.IW2BR.domain.exam.answer;

import com.bella.IW2BR.domain.exam.question.Question;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answers")
@Data
@NoArgsConstructor
public class Answer {
  @Builder
  public Answer(String answer, boolean isCorrect, Question question) {
    this.answer = answer;
    this.isCorrect = isCorrect;
    this.question = question;
  }

  @Id @GeneratedValue private Long id;

  @Column(name = "answer", nullable = false)
  private String answer;

  @Column(name = "is_correct", nullable = false)
  private boolean isCorrect;

  @ManyToOne
  @JoinColumn(name = "question_id", nullable = false)
  @JsonBackReference
  private Question question;
}
