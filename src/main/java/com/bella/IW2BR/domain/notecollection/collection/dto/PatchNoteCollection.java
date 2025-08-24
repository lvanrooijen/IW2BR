package com.bella.IW2BR.domain.notecollection.collection.dto;

import static com.bella.IW2BR.utils.constants.GlobalValidationConstraints.*;

import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
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
        String description) {
  /**
   * Updates the field and returns the patched {@link NoteCollection}
   *
   * @param noteCollection {@link NoteCollection}
   * @param patch {@link PatchNoteCollection}
   * @return {@link NoteCollection}
   */
  public static NoteCollection patch(NoteCollection noteCollection, PatchNoteCollection patch) {

    if (patch.title() != null) {
      noteCollection.setTitle(patch.title());
    }
    if (patch.description() != null) {
      noteCollection.setDescription(patch.description());
    }

    return noteCollection;
  }
}
