package com.bella.IW2BR.domain.exam.exam;

import com.bella.IW2BR.domain.environment.Environment;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
public class Exam {
  @Builder
  public Exam(String title, String description, LocalDate createdAt, Environment environment) {
    this.title = title;
    this.description = description;
    this.createdAt = createdAt;
    this.environment = environment;
  }

  @Id @GeneratedValue private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "environment_id")
  private Environment environment;
}
