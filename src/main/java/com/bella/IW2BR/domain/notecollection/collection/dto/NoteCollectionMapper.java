package com.bella.IW2BR.domain.notecollection.collection.dto;

import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import org.springframework.stereotype.Service;

/**
 * Service that handles mapping between DTOs and the entity.
 *
 * <p>Methods:
 *
 * <ul>
 *   <li>Map {@link PostNoteCollection} DTO to {@link NoteCollection} entity
 *   <li>Map {@link NoteCollection} entity to {@link GetNoteCollection}
 *   <li>Patch {@link NoteCollection} with new data from {@link PatchNoteCollection}
 * </ul>
 */
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
