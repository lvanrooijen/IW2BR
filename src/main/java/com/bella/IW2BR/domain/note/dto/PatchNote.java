package com.bella.IW2BR.domain.note.dto;

import static com.bella.IW2BR.domain.note.dto.NoteConstraints.*;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;

import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Note PATCH request
 *
 * @param title
 * @param body
 * @param tagId
 */
public record PatchNote(
    @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG) String title,
    @Length(min = BODY_MIN, max = BODY_MAX, message = INVALID_BODY_LENGTH_MSG) String body,
    Long tagId) {}
