package com.bella.IW2BR.domain.notecollection.dto;

import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;

import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Note-collection PATCH request
 *
 * @param title
 * @param description
 */
public record PatchNoteCollection(
    @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG) String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {}
