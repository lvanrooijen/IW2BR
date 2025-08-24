package com.bella.IW2BR.domain.flashcarddeck.deck.dto;

import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MAX;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MIN;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.INVALID_DESCRIPTION_LENGTH_MSG;

import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Flashcard-Deck PATCH request
 *
 * @param title
 * @param description
 */
public record PatchFlashcardDeck(
    @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG) String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {
  /**
   * Updates fields of {@link FlashcardDeck}
   *
   * @param flashcardDeck {@link FlashcardDeck}
   * @param patch {@link PatchFlashcardDeck}
   * @return updated {@link FlashcardDeck}
   */
  public static FlashcardDeck patch(FlashcardDeck flashcardDeck, PatchFlashcardDeck patch) {
    if (patch.title() != null) {
      flashcardDeck.setTitle(patch.title());
    }
    if (patch.description() != null) {
      flashcardDeck.setDescription(patch.description());
    }
    return flashcardDeck;
  }
}
