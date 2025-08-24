package com.bella.IW2BR.domain.notecollection.note.dto;

import static com.bella.IW2BR.domain.notecollection.note.dto.NoteConstraints.*;

import com.bella.IW2BR.domain.notecollection.note.Note;
import com.bella.IW2BR.domain.tag.Tag;
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
    @Positive Long tagId) {
  /**
   * Updates fields and returns Patched {@link Note}
   *
   * @param note {@link Note}
   * @param patch {@link PatchNote}
   * @return {@link Note}
   */
  public static Note patch(Note note, PatchNote patch) {
    if (patch.title() != null) {
      note.setTitle(patch.title());
    }
    if (patch.body() != null) {
      note.setBody(patch.body());
    }
    return note;
  }

  /**
   * Updates fields and returns Patched {@link Note}
   *
   * @param note {@link Note}
   * @param patch {@link PatchNote}
   * @param tag {@link Tag}
   * @return {@link Note}
   */
  public static Note patch(Note note, PatchNote patch, Tag tag) {
    patch(note, patch);
    note.setTag(tag);
    return note;
  }
}
