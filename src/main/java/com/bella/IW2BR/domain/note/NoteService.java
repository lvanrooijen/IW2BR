package com.bella.IW2BR.domain.note;

import com.bella.IW2BR.domain.environment.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.note.dto.GetNote;
import com.bella.IW2BR.domain.note.dto.NoteMapper;
import com.bella.IW2BR.domain.note.dto.PatchNote;
import com.bella.IW2BR.domain.note.dto.PostNote;
import com.bella.IW2BR.domain.notecollection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.NoteCollectionRepository;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
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

  public GetNote createNote(Long environmentId, Long noteCollectionId, PostNote body) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);

    NoteCollection noteCollection =
        noteCollectionRepository
            .findById(noteCollectionId)
            .orElseThrow(() -> new ItemNotFoundException("note Collection not found"));
    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);

    Note note;
    if (body.tagId() != null) {
      Tag tag = environmentHelperMethods.getTagOrThrow(body.tagId());
      note = noteMapper.fromPostNote(body, noteCollection, tag);
    } else {
      note = noteMapper.fromPostNote(body, noteCollection);
    }

    noteRepository.save(note);

    return noteMapper.toGetNote(note);
  }

  public GetNote getNoteById(Long environmentId, Long noteCollectionId, Long id) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    NoteCollection noteCollection =
        environmentHelperMethods.getNoteCollectionOrThrow(noteCollectionId);
    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);
    Note note = environmentHelperMethods.getNoteOrThrow(id);

    return noteMapper.toGetNote(note);
  }

  public List<GetNote> getAllNotes(Long environmentId, Long noteCollectionId) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    NoteCollection noteCollection =
        environmentHelperMethods.getNoteCollectionOrThrow(noteCollectionId);
    List<Note> notes = noteRepository.findByNotecollection(noteCollection);

    return notes.stream().map(noteMapper::toGetNote).toList();
  }

  public GetNote updateNote(Long environmentId, Long noteCollectionId, Long id, PatchNote patch) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    Note note = environmentHelperMethods.getNoteOrThrow(id);
    if (patch.tagId() != null) {
      Tag tag = environmentHelperMethods.getTagOrThrow(patch.tagId());
      noteMapper.updateNoteFields(note, patch, tag);
    } else {
      noteMapper.updateNoteFields(note, patch);
    }
    noteRepository.save(note);
    return noteMapper.toGetNote(note);
  }

  public void deleteNote(Long environmentId, Long noteCollectionId, Long id) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(environmentId);
    NoteCollection noteCollection =
        environmentHelperMethods.getNoteCollectionOrThrow(noteCollectionId);
    environmentHelperMethods.throwIfNotInEnvironment(noteCollection, environmentId);
    environmentHelperMethods.getNoteOrThrow(id);
    noteRepository.deleteById(id);
  }
}
