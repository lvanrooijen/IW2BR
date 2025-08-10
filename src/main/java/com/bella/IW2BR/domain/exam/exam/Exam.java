package com.bella.IW2BR.domain.exam.exam;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentMember;
import com.bella.IW2BR.domain.exam.question.Question;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
public class Exam implements EnvironmentMember {
  @Builder
  public Exam(
      String title,
      String description,
      LocalDate createdAt,
      boolean isFinalised,
      Environment environment) {
    this.title = title;
    this.description = description;
    this.createdAt = createdAt;
    this.environment = environment;
    this.isFinalised = isFinalised;
  }

  @Id @GeneratedValue private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "is_finalised", nullable = false)
  private boolean isFinalised;

  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "environment_id")
  private Environment environment;

  @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Question> questions;
}
