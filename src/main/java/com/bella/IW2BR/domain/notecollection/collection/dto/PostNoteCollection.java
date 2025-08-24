package com.bella.IW2BR.domain.notecollection.collection.dto;

import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MAX;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.DESCRIPTION_MIN;
import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.INVALID_DESCRIPTION_LENGTH_MSG;

import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Note-collection POST request
 *
 * @param title
 * @param description
 */
public record PostNoteCollection(
    @NotBlank @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG)
        String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {
  /**
   * Maps {@link PostNoteCollection} to {@link NoteCollection}
   *
   * @param dto {@link PostNoteCollection}
   * @return {@link NoteCollection}
   */
  public static NoteCollection from(PostNoteCollection dto) {
    return NoteCollection.builder().title(dto.title()).description(dto.description()).build();
  }
}
