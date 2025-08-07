package com.bella.IW2BR.domain.flashcard;

import com.bella.IW2BR.domain.flashcarddeck.FlashcardDeck;
import com.bella.IW2BR.domain.tag.Tag;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "flashcards")
@Data
public class Flashcard {
  @Builder
  public Flashcard(
      String frontBody,
      String backBody,
      LocalDate lastSeen,
      int positiveFlags,
      int negativeFlags,
      FlashcardDeck flashcardDeck,
      Tag tag) {
    this.frontBody = frontBody;
    this.backBody = backBody;
    this.lastSeen = lastSeen;
    this.positiveFlags = positiveFlags;
    this.negativeFlags = negativeFlags;
    this.flashcardDeck = flashcardDeck;
    this.tag = tag;
  }

  @Id @GeneratedValue private Long id;

  @Column(name = "front_body", nullable = false)
  private String frontBody;

  @Column(name = "back_body", nullable = false)
  private String backBody;

  @Column(name = "last_seen", nullable = true)
  private LocalDate lastSeen;

  @Column(name = "positive_flags", nullable = false)
  private int positiveFlags;

  @Column(name = "negative_flags", nullable = false)
  private int negativeFlags;

  @ManyToOne
  @JoinColumn(name = "flashcard_deck_id", nullable = false)
  private FlashcardDeck flashcardDeck;

  @ManyToOne
  @JoinColumn(name = "tag_id", nullable = true)
  private Tag tag;
}
