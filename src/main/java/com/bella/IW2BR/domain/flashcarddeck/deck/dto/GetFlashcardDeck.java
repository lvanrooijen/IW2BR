package com.bella.IW2BR.domain.flashcarddeck.deck.dto;

import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.GetFlashcard;
import java.util.List;

/**
 * DTO representing how a Flashcard Deck is returned to the client
 *
 * @param id
 * @param title
 * @param description
 */
public record GetFlashcardDeck(Long id, String title, String description, List<GetFlashcard> flashcards) {
  /**
   * Maps {@link FlashcardDeck} to {@link GetFlashcardDeck}
   *
   * @param flashcardDeck {@link FlashcardDeck}
   * @return {@link GetFlashcardDeck}
   */
  public static GetFlashcardDeck to(FlashcardDeck flashcardDeck) {
            List<GetFlashcard> flashcards = flashcardDeck.getFlashcards().stream().map(GetFlashcard::to).toList();
    return new GetFlashcardDeck(
        flashcardDeck.getId(), flashcardDeck.getTitle(), flashcardDeck.getDescription(), flashcards);
  }
}
