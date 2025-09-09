package com.bella.IW2BR.domain.notecollection.collection.dto;

import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.note.dto.GetNote;
import java.util.List;

/**
 * TO representing how the Note-collection is returned to the client
 *
 * @param id
 * @param title
 * @param description
 * @param notes List of {@link GetNote}
 */
public record GetNoteCollection(Long id, String title, String description, List<GetNote> notes) {
  /**
   * Maps {@link NoteCollection} to {@link GetNoteCollection}
   *
   * @param noteCollection {@link NoteCollection}
   * @return {@link GetNoteCollection}
   */
  public static GetNoteCollection to(NoteCollection noteCollection) {
    List<GetNote> notes =
        noteCollection.getNotes() != null
            ? noteCollection.getNotes().stream().map(GetNote::to).toList()
            : null;
    return new GetNoteCollection(
        noteCollection.getId(), noteCollection.getTitle(), noteCollection.getDescription(), notes);
  }
}
