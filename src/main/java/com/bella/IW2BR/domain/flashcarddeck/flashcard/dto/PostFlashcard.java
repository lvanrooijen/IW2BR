package com.bella.IW2BR.domain.flashcarddeck.flashcard.dto;

import static com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.FlashcardConstraints.*;

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
    @Positive Long tagId) {}
