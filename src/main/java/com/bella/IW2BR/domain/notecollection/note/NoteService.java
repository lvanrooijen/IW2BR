package com.bella.IW2BR.domain.notecollection.note;

import com.bella.IW2BR.domain.environment.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollectionRepository;
import com.bella.IW2BR.domain.notecollection.note.dto.GetNote;
import com.bella.IW2BR.domain.notecollection.note.dto.NoteMapper;
import com.bella.IW2BR.domain.notecollection.note.dto.PatchNote;
import com.bella.IW2BR.domain.notecollection.note.dto.PostNote;
import com.bella.IW2BR.domain.tag.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteMapper noteMapper;
  private final NoteRepository noteRepository;
  private final NoteCollectionRepository noteCollectionRepository;
  private final EnvironmentHelperMethods environmentHelperMethods;

  public GetNote create(Long environmentId, Long noteCollectionId, PostNote body) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);

    NoteCollection noteCollection =
        environmentHelperMethods.getNoteCollectionOrThrow(noteCollectionId);

    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);

    Note note;
    if (body.tagId() != null) {
      Tag tag = environmentHelperMethods.getTagOrThrow(body.tagId());
      note = noteMapper.fromPost(body, noteCollection, tag);
    } else {
      note = noteMapper.fromPost(body, noteCollection);
    }

    noteRepository.save(note);

    return noteMapper.toGet(note);
  }

  public GetNote getById(Long environmentId, Long noteCollectionId, Long id) {
    // TODO is note member van note collection?
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    NoteCollection noteCollection =
        environmentHelperMethods.getNoteCollectionOrThrow(noteCollectionId);
    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);
    Note note = environmentHelperMethods.getNoteOrThrow(id);

    return noteMapper.toGet(note);
  }

  public List<GetNote> getAll(Long environmentId, Long noteCollectionId) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    NoteCollection noteCollection =
        environmentHelperMethods.getNoteCollectionOrThrow(noteCollectionId);
    List<Note> notes = noteRepository.findByNotecollection(noteCollection);

    return notes.stream().map(noteMapper::toGet).toList();
  }

  public GetNote update(Long environmentId, Long noteCollectionId, Long id, PatchNote patch) {
    // TODO is note member van note collection?
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    Note note = environmentHelperMethods.getNoteOrThrow(id);
    if (patch.tagId() != null) {
      Tag tag = environmentHelperMethods.getTagOrThrow(patch.tagId());
      noteMapper.updateFields(note, patch, tag);
    } else {
      noteMapper.updateFields(note, patch);
    }

    noteRepository.save(note);
    return noteMapper.toGet(note);
  }

  public void delete(Long environmentId, Long noteCollectionId, Long id) {
    // TODO is note member van note collection?
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    NoteCollection noteCollection =
        environmentHelperMethods.getNoteCollectionOrThrow(noteCollectionId);
    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);
    environmentHelperMethods.getNoteOrThrow(id);
    noteRepository.deleteById(id);
  }
}
