package com.bella.IW2BR.domain.flashcarddeck.deck.dto;

import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MAX;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MIN;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.INVALID_DESCRIPTION_LENGTH_MSG;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Flashcard-Deck POST request
 *
 * @param title
 * @param description
 */
public record PostFlashcardDeck(
    @NotBlank @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG)
        String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {

  /**
   * Maps {@link PostFlashcardDeck} to {@link FlashcardDeck}
   *
   * @param dto {@link PostFlashcardDeck}
   * @param environment {@link Environment}
   * @return {@link FlashcardDeck}
   */
  public static FlashcardDeck from(PostFlashcardDeck dto, Environment environment) {
    return FlashcardDeck.builder()
        .title(dto.title())
        .description(dto.description())
        .environment(environment)
        .build();
  }
}
