package com.bella.IW2BR.domain.flashcarddeck;

import com.bella.IW2BR.domain.environment.Environment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "flashcard_decks")
@Data
public class FlashcardDeck {
  @Builder
  public FlashcardDeck(String title, String description, Environment environment) {
    this.title = title;
    this.description = description;
    this.environment = environment;
  }

  @Id @GeneratedValue Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = true)
  private String description;

  @ManyToOne
  @JoinColumn(name = "environment_id", nullable = false)
  private Environment environment;
}
