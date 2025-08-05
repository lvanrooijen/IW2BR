package com.bella.IW2BR.domain.notecollection.dto;

import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MAX_LENGTH;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MIN_LENGTH;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.INVALID_DESCRIPTION_LENGTH_MSG;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record PostNoteCollection(
    @NotBlank
        @Length(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH, message = INVALID_TITLE_LENGTH_MSG)
        String title,
    @Length(
            min = DESCRIPTION_MIN_LENGTH,
            max = DESCRIPTION_MAX_LENGTH,
            message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {}
