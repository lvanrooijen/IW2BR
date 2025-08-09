package com.bella.IW2BR.domain.exam.exam.dto;

import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MAX;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MIN;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.INVALID_DESCRIPTION_LENGTH_MSG;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of an Exam PATCH request
 *
 * @param title
 * @param description
 */
public record PostExam(
    @NotBlank @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG)
        String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {}
