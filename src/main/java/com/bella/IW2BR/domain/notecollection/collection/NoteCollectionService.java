package com.bella.IW2BR.domain.notecollection.collection;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.notecollection.collection.dto.GetNoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.dto.NoteCollectionMapper;
import com.bella.IW2BR.domain.notecollection.collection.dto.PatchNoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.dto.PostNoteCollection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteCollectionService {
  private final NoteCollectionRepository noteCollectionRepository;
  private final NoteCollectionMapper noteCollectionMapper;
  private final EnvironmentHelperMethods environmentHelperMethods;

  public GetNoteCollection create(Long environmentId, PostNoteCollection body) {
    NoteCollection noteCollection = noteCollectionMapper.fromPost(body);
    Environment environment = environmentHelperMethods.getEnvironmentOrThrow(environmentId);

    environmentHelperMethods.throwIfNotOwnerOrAdmin(environment);

    noteCollection.setEnvironment(environment);
    noteCollectionRepository.save(noteCollection);

    return noteCollectionMapper.toGet(noteCollection);
  }

  public GetNoteCollection getById(Long environmentId, Long id) {
    NoteCollection noteCollection = environmentHelperMethods.getNoteCollectionOrThrow(id);

    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);

    return noteCollectionMapper.toGet(noteCollection);
  }

  public List<GetNoteCollection> getAll(Long environmentId) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    List<NoteCollection> noteCollections =
        noteCollectionRepository.findAllByEnvironmentId(environmentId);

    return noteCollections.stream().map(noteCollectionMapper::toGet).toList();
  }

  public GetNoteCollection update(Long environmentId, Long id, PatchNoteCollection patch) {
    NoteCollection noteCollection = environmentHelperMethods.getNoteCollectionOrThrow(id);

    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);

    noteCollectionMapper.updateFields(noteCollection, patch);
    noteCollectionRepository.save(noteCollection);

    noteCollectionRepository.save(noteCollection);
    return noteCollectionMapper.toGet(noteCollection);
  }

  public void delete(Long environmentId, Long id) {
    NoteCollection noteCollection = environmentHelperMethods.getNoteCollectionOrThrow(id);

    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);

    noteCollectionRepository.deleteById(id);
  }
}
