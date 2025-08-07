package com.bella.IW2BR.domain.notecollection.dto;

import com.bella.IW2BR.domain.notecollection.NoteCollection;
import org.springframework.stereotype.Service;

@Service
public class NoteCollectionMapper {
  public GetNoteCollection toGet(NoteCollection entity) {
    return new GetNoteCollection(entity.getId(), entity.getTitle(), entity.getDescription());
  }

  public NoteCollection fromPost(PostNoteCollection dto) {
    return NoteCollection.builder().title(dto.title()).description(dto.description()).build();
  }

  public NoteCollection updateFields(NoteCollection noteCollection, PatchNoteCollection patch) {
    if (patch.title() != null) {
      noteCollection.setTitle(patch.title());
    }
    if (patch.description() != null) {
      noteCollection.setDescription(patch.description());
    }

    return noteCollection;
  }
}
