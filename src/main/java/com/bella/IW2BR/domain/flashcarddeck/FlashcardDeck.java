package com.bella.IW2BR.domain.flashcarddeck;

import com.bella.IW2BR.domain.environment.Environment;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "flashcard_decks")
@Data
public class FlashcardDeck {
  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = true)
  private String description;

  @ManyToOne
  @JoinColumn(name = "environment_id", nullable = false)
  private Environment environment;
}
