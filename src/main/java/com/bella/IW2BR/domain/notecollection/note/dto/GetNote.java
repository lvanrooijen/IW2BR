package com.bella.IW2BR.domain.notecollection.note.dto;

import com.bella.IW2BR.domain.notecollection.note.Note;

/**
 * DTO representing how the Note is returned to the client
 *
 * @param id
 * @param title
 * @param body
 * @param tagId
 */
public record GetNote(Long id, String title, String body, Long tagId) {
  /**
   * Maps a {@link Note} to {@link GetNote} dto
   *
   * @param note {@link Note}
   * @return {@link GetNote}
   */
  public static GetNote to(Note note) {
    Long tagId = note.getTag() != null ? note.getTag().getId() : null;
    return new GetNote(note.getId(), note.getTitle(), note.getBody(), tagId);
  }
}
