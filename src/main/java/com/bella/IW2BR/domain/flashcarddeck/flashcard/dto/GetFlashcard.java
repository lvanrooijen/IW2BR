package com.bella.IW2BR.domain.flashcarddeck.flashcard.dto;

import com.bella.IW2BR.domain.flashcarddeck.flashcard.Flashcard;

/**
 * DTO representing how the Flashcard is returned to the client
 *
 * @param id
 * @param frontBody
 * @param backBody
 * @param flashcardDeckId
 * @param tagId
 */
public record GetFlashcard(
    Long id, String frontBody, String backBody, Long flashcardDeckId, Long tagId) {

  /**
   * Maps {@link Flashcard} to {@link GetFlashcard}
   *
   * @param flashcard {@link Flashcard}
   * @return {@link GetFlashcard}
   */
  public static GetFlashcard to(Flashcard flashcard) {
    Long tagId = flashcard.getTag() == null ? null : flashcard.getTag().getId();

    return new GetFlashcard(
        flashcard.getId(),
        flashcard.getFrontBody(),
        flashcard.getBackBody(),
        flashcard.getFlashcardDeck().getId(),
        tagId);
  }
}
