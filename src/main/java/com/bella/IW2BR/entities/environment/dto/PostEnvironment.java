package com.bella.IW2BR.entities.environment.dto;

import static com.bella.IW2BR.entities.environment.dto.EnvironmentConstraints.*;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record PostEnvironment(
    @NotBlank
        @Length(min = TITLE_MIN_LENGTH, max = TITLE_MAX_LENGTH, message = INVALID_TITLE_LENGTH_MSG)
        String title,
    @Length(
            min = DESCRIPTION_MIN_LENGTH,
            max = DESCRIPTION_MAX_LENGTH,
            message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {}
