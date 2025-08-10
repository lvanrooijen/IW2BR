package com.bella.IW2BR.domain.notecollection.note.dto;

import static com.bella.IW2BR.domain.notecollection.note.dto.NoteConstraints.*;

import jakarta.validation.constraints.Positive;
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
    @Positive Long tagId) {}
