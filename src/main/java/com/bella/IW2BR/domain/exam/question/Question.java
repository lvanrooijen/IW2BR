package com.bella.IW2BR.domain.exam.question;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.tag.Tag;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
public class Question {
  @Builder
  public Question(String question, int answerAmount, Exam exam, Tag tag) {
    Question = question;
    this.answerAmount = answerAmount;
    this.exam = exam;
    this.tag = tag;
  }

  @Id @GeneratedValue private Long id;

  @Column(name = "deleted", nullable = false)
  private boolean isDeleted = false;

  @Column(name = "question", nullable = false)
  private String Question;

  @Column(name = "answer_amount", nullable = false)
  private int answerAmount;

  @ManyToOne
  @JoinColumn(name = "exam_id", nullable = false)
  private Exam exam;

  @ManyToOne
  @JoinColumn(name = "tag_id", nullable = true)
  private Tag tag;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Answer> answers;
}
