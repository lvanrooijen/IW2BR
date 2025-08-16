package com.bella.IW2BR.domain.exam.attempt;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentMember;
import com.bella.IW2BR.domain.exam.exam.Exam;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exam_attempts")
@Data
@NoArgsConstructor
public class ExamAttempt implements EnvironmentMember {
  @Builder
  public ExamAttempt(
      LocalDateTime startedAt,
      LocalDateTime completedAt,
      Boolean isCompleted,
      double score,
      Exam exam,
      Environment environment) {
    this.startedAt = startedAt;
    this.completedAt = completedAt;
    this.isCompleted = isCompleted;
    this.score = score;
    this.exam = exam;
    this.environment = environment;
  }

  @Id @GeneratedValue private Long id;

  @Column(name = "started_at", nullable = false)
  private LocalDateTime startedAt;

  @Column(name = "completed_at", nullable = true)
  private LocalDateTime completedAt;

  @Column(name = "is_completed", nullable = true)
  private Boolean isCompleted;

  @Column(name = "score", nullable = false)
  private double score;

  @ManyToOne
  @JoinColumn(name = "exam_id", nullable = false)
  private Exam exam;

  @ManyToOne
  @JoinColumn(name = "environment_id", nullable = false)
  private Environment environment;

}
