package com.bella.IW2BR.domain.environment;

import com.bella.IW2BR.domain.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Represents environment related to a particular study subject. */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "environments")
public class Environment {
  @Builder
  public Environment(String title, String description, LocalDate createdAt, User owner) {
    this.title = title;
    this.description = description;
    this.createdAt = createdAt;
    this.owner = owner;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "owner", nullable = false)
  private User owner;
}
