package com.bella.IW2BR.domain.notecollection.note.dto;

import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.note.Note;
import com.bella.IW2BR.domain.tag.Tag;
import org.springframework.stereotype.Service;

/**
 * Service that handles mapping between DTOs and the entity.
 *
 * <p>Methods:
 *
 * <ul>
 *   <li>Map {@link PostNote} DTO to {@link Note} entity
 *   <li>Map {@link Note} entity to {@link GetNote}
 *   <li>Patch {@link Note} with new data from {@link PatchNote}
 * </ul>
 */
@Service
public class NoteMapper {

  public GetNote toGet(Note note) {
    Long tagId = note.getTag() != null ? note.getTag().getId() : null;
    return new GetNote(
        note.getId(), note.getTitle(), note.getBody(), note.getNotecollection().getId(), tagId);
  }

  public Note fromPost(PostNote entity, NoteCollection noteCollection) {
    return Note.builder()
        .title(entity.title())
        .body(entity.body())
        .noteCollection(noteCollection)
        .build();
  }

  public Note fromPost(PostNote entity, NoteCollection noteCollection, Tag tag) {
    Note note = fromPost(entity, noteCollection);
    note.setTag(tag);
    return note;
  }

  public Note updateFields(Note note, PatchNote patch) {
    if (patch.title() != null) {
      note.setTitle(patch.title());
    }
    if (patch.body() != null) {
      note.setBody(patch.body());
    }
    return note;
  }

  public Note updateFields(Note note, PatchNote patch, Tag tag) {
    updateFields(note, patch);
    note.setTag(tag);
    return note;
  }
}
