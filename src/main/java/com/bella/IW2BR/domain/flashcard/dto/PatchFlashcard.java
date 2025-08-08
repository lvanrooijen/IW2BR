package com.bella.IW2BR.domain.flashcard.dto;

import static com.bella.IW2BR.domain.flashcard.dto.FlashcardConstraints.*;
import static com.bella.IW2BR.domain.flashcard.dto.FlashcardConstraints.BACK_BODY_MAX;
import static com.bella.IW2BR.domain.flashcard.dto.FlashcardConstraints.BODY_MIN;
import static com.bella.IW2BR.domain.flashcard.dto.FlashcardConstraints.INVALID_BACK_BODY_LENGTH_MSG;

import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Flashcard PATCH request
 *
 * @param frontBody
 * @param backBody
 * @param tagId
 */
public record PatchFlashcard(
    @Length(min = BODY_MIN, max = FRONT_BODY_MAX, message = INVALID_FRONT_BODY_LENGTH_MSG)
        String frontBody,
    @Length(min = BODY_MIN, max = BACK_BODY_MAX, message = INVALID_BACK_BODY_LENGTH_MSG)
        String backBody,
    @Positive Long tagId) {}
