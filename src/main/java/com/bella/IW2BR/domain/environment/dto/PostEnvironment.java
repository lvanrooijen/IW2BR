package com.bella.IW2BR.domain.environment.dto;

import static com.bella.IW2BR.domain.environment.dto.EnvironmentConstraintsGlobal.*;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record PostEnvironment(
    @NotBlank @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG)
        String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {}
