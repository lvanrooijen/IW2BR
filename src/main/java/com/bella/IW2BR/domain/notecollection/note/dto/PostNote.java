package com.bella.IW2BR.domain.notecollection.note.dto;

import static com.bella.IW2BR.domain.notecollection.note.dto.NoteConstraints.*;

import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.note.Note;
import com.bella.IW2BR.domain.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Note POST request
 *
 * @param title
 * @param body
 * @param tagId
 */
public record PostNote(
    @NotBlank @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG)
        String title,
    @NotBlank @Length(min = BODY_MIN, max = BODY_MAX, message = INVALID_BODY_LENGTH_MSG)
        String body,
    @Positive Long tagId) {
  /**
   * Maps {@link PostNote} to {@link Note}
   *
   * @param note {@link Note}
   * @param noteCollection {@link NoteCollection}
   * @return {@link Note}
   */
  public static Note from(PostNote note, NoteCollection noteCollection) {
    return Note.builder()
        .title(note.title())
        .body(note.body())
        .noteCollection(noteCollection)
        .build();
  }

  /**
   * Maps {@link PostNote} to {@link Note}
   *
   * @param note {@link Note}
   * @param noteCollection {@link NoteCollection}
   * @param tag {@link Tag}
   * @return {@link Note}
   */
  public static Note from(PostNote note, NoteCollection noteCollection, Tag tag) {
    Note createdNote = from(note, noteCollection);
    createdNote.setTag(tag);
    return createdNote;
  }
}
