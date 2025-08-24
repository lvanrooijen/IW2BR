package com.bella.IW2BR.domain.flashcarddeck.flashcard.dto;

import static com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.FlashcardConstraints.*;

import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.Flashcard;
import com.bella.IW2BR.domain.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Flashcard-Deck POST request
 *
 * @param frontBody content that represent the front of the flashcard
 * @param backBody content that represents the back of the flashcard
 * @param tagId ID of Tag
 */
public record PostFlashcard(
    @NotBlank @Length(min = BODY_MIN, max = FRONT_BODY_MAX, message = INVALID_FRONT_BODY_LENGTH_MSG)
        String frontBody,
    @NotBlank @Length(min = BODY_MIN, max = BACK_BODY_MAX, message = INVALID_BACK_BODY_LENGTH_MSG)
        String backBody,
    @Positive Long tagId) {

  /**
   * Maps {@link PostFlashcard} to {@link Flashcard}
   *
   * @param dto {@link PostFlashcard}
   * @param flashcardDeck {@link Flashcard}
   * @return created {@link Flashcard}
   */
  public static Flashcard from(PostFlashcard dto, FlashcardDeck flashcardDeck) {
    return Flashcard.builder()
        .frontBody(dto.frontBody())
        .backBody(dto.backBody())
        .positiveFlags(0)
        .negativeFlags(0)
        .flashcardDeck(flashcardDeck)
        .build();
  }

  /**
   * Maps {@link PostFlashcard} to {@link Flashcard}
   *
   * @param dto {@link PostFlashcard}
   * @param flashcardDeck {@link Flashcard}
   * @param tag {@link Tag}
   * @return created {@link Flashcard}
   */
  public static Flashcard from(PostFlashcard dto, FlashcardDeck flashcardDeck, Tag tag) {
    Flashcard flashcard = from(dto, flashcardDeck);
    flashcard.setTag(tag);

    return flashcard;
  }
}
