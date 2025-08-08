package com.bella.IW2BR.domain.flashcarddeck.dto;

import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MAX;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MIN;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.INVALID_DESCRIPTION_LENGTH_MSG;

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
        String description) {}
