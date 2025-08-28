package com.bella.IW2BR.domain.notecollection.note.dto;

import com.bella.IW2BR.domain.notecollection.note.Note;
import com.bella.IW2BR.domain.tag.dto.GetTagCompact;

/**
 * DTO representing how the Note is returned to the client
 *
 * @param id
 * @param title
 * @param body
 * @param tag
 */
public record GetNote(Long id, String title, String body, GetTagCompact tag) {
  /**
   * Maps a {@link Note} to {@link GetNote} dto
   *
   * @param note {@link Note}
   * @return {@link GetNote}
   */
  public static GetNote to(Note note) {
    GetTagCompact tag = note.getTag() != null ? GetTagCompact.to(note.getTag()) : null;
    return new GetNote(note.getId(), note.getTitle(), note.getBody(), tag);
  }
}
