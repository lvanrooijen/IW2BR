package com.bella.IW2BR.domain.note.dto;

import com.bella.IW2BR.domain.note.Note;
import com.bella.IW2BR.domain.notecollection.NoteCollection;
import com.bella.IW2BR.domain.tag.Tag;
import org.springframework.stereotype.Service;

@Service
public class NoteMapper {

  public GetNote toGetNote(Note note) {
    return new GetNote(
        note.getId(),
        note.getTitle(),
        note.getBody(),
        note.getNotecollection().getId(),
        note.getTag().getId());
  }

  public Note fromPostNote(PostNote entity, NoteCollection noteCollection) {
    return Note.builder()
        .title(entity.title())
        .body(entity.body())
        .notecollection(noteCollection)
        .build();
  }

  public Note fromPostNote(PostNote entity, NoteCollection noteCollection, Tag tag) {
    Note note = fromPostNote(entity, noteCollection);
    note.setTag(tag);
    return note;
  }

  public Note updateNoteFields(Note note, PatchNote patch) {
    if (patch.title() != null) {
      note.setTitle(patch.title());
    }
    if (patch.body() != null) {
      note.setBody(patch.body());
    }
    return note;
  }

  public Note updateNoteFields(Note note, PatchNote patch, Tag tag) {
    updateNoteFields(note, patch);
    note.setTag(tag);
    return note;
  }
}
