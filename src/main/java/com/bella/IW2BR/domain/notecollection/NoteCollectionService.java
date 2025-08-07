package com.bella.IW2BR.domain.notecollection;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.notecollection.dto.GetNoteCollection;
import com.bella.IW2BR.domain.notecollection.dto.NoteCollectionMapper;
import com.bella.IW2BR.domain.notecollection.dto.PatchNoteCollection;
import com.bella.IW2BR.domain.notecollection.dto.PostNoteCollection;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteCollectionService {
  private final NoteCollectionRepository noteCollectionRepository;
  private final NoteCollectionMapper noteCollectionMapper;
  private final EnvironmentHelperMethods environmentHelperMethods;

  public GetNoteCollection createNoteCollection(Long environmentId, PostNoteCollection body) {
    NoteCollection noteCollection = noteCollectionMapper.fromPost(body);
    Environment environment = environmentHelperMethods.getEnvironmentOrThrow(environmentId);

    environmentHelperMethods.throwIfNotOwnerOrAdmin(environment);

    noteCollection.setEnvironment(environment);
    noteCollectionRepository.save(noteCollection);

    return noteCollectionMapper.toGet(noteCollection);
  }

  public GetNoteCollection getNoteCollectionById(Long environmentId, Long id) {
    NoteCollection noteCollection = getNoteCollectionOrThrow(id);

    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);

    return noteCollectionMapper.toGet(noteCollection);
  }

  public List<GetNoteCollection> getAllNoteCollections(Long environmentId) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    List<NoteCollection> noteCollections =
        noteCollectionRepository.findAllByEnvironmentId(environmentId);

    return noteCollections.stream().map(noteCollectionMapper::toGet).toList();
  }

  public GetNoteCollection updateNoteCollection(
      Long environmentId, Long id, PatchNoteCollection patch) {
    NoteCollection noteCollection = getNoteCollectionOrThrow(id);

    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);

    noteCollectionMapper.updateFields(noteCollection, patch);
    noteCollectionRepository.save(noteCollection);

    return noteCollectionMapper.toGet(noteCollection);
  }

  public void deleteCollection(Long environmentId, Long id) {
    NoteCollection noteCollection = getNoteCollectionOrThrow(id);

    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);

    noteCollectionRepository.deleteById(id);
  }

  /**
   * Helper method, fetches Note Collection or throws Exception
   *
   * @param id ID of note collection
   * @return {@link NoteCollection}
   * @throws ItemNotFoundException when the note collection does not exist
   */
  private NoteCollection getNoteCollectionOrThrow(Long id) {

    return noteCollectionRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Note collection not found"));
  }
}
