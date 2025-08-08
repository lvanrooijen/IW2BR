package com.bella.IW2BR.domain.flashcarddeck;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.flashcard.Flashcard;
import jakarta.persistence.*;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flashcard_decks")
@Data
@NoArgsConstructor
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

  @OneToMany(mappedBy = "flashcardDeck", cascade = CascadeType.ALL, orphanRemoval = true)
  List<Flashcard> flashcards;
}
