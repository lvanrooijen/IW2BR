package com.bella.IW2BR.domain.notecollection.note;

import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.note.dto.GetNote;
import com.bella.IW2BR.domain.notecollection.note.dto.PatchNote;
import com.bella.IW2BR.domain.notecollection.note.dto.PostNote;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.exceptions.environment.MismatchingIdException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;
  private final EnvironmentHelperMethods helperMethods;

  public GetNote create(Long environmentId, Long noteCollectionId, PostNote body) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    NoteCollection noteCollection = helperMethods.getNoteCollectionOrThrow(noteCollectionId);

    helperMethods.throwIfNotInEnvironment(noteCollection, environmentId);

    Note note;
    if (body.tagId() != null) {
      Tag tag = helperMethods.getTagOrThrow(body.tagId());
      note = PostNote.from(body, noteCollection, tag);
    } else {
      note = PostNote.from(body, noteCollection);
    }

    noteRepository.save(note);

    return GetNote.to(note);
  }

  public GetNote getById(Long environmentId, Long noteCollectionId, Long id) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    NoteCollection noteCollection = helperMethods.getNoteCollectionOrThrow(noteCollectionId);
    helperMethods.throwIfNotInEnvironment(noteCollection, environmentId);

    Note note = helperMethods.getNoteOrThrow(id);
    throwIfNoteCollectionIdMismatch(noteCollectionId, note);

    return GetNote.to(note);
  }

  public List<GetNote> getAll(Long environmentId, Long noteCollectionId) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    NoteCollection noteCollection = helperMethods.getNoteCollectionOrThrow(noteCollectionId);
    List<Note> notes = noteRepository.findByNotecollection(noteCollection);

    return notes.stream().map(GetNote::to).toList();
  }

  public GetNote update(Long environmentId, Long noteCollectionId, Long id, PatchNote patch) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Note note = helperMethods.getNoteOrThrow(id);
    throwIfNoteCollectionIdMismatch(noteCollectionId, note);

    if (patch.tagId() != null) {
      Tag tag = helperMethods.getTagOrThrow(patch.tagId());
      PatchNote.patch(note, patch, tag);
    } else {
      PatchNote.patch(note, patch);
    }

    noteRepository.save(note);
    return GetNote.to(note);
  }

  public void delete(Long environmentId, Long noteCollectionId, Long id) {
    helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    NoteCollection noteCollection = helperMethods.getNoteCollectionOrThrow(noteCollectionId);
    helperMethods.throwIfNotInEnvironment(noteCollection, environmentId);

    Note note = helperMethods.getNoteOrThrow(id);
    throwIfNoteCollectionIdMismatch(noteCollectionId, note);

    noteRepository.deleteById(id);
  }

  /**
   * Verifies if the noteCollectionId in the path variable is the same as the note-collection
   * connected to the note
   *
   * @param noteCollectionId ID of flashcard provided through the path variable
   * @param note {@link Note}
   * @throws MismatchingIdException when the ID's aren't the same
   */
  private void throwIfNoteCollectionIdMismatch(Long noteCollectionId, Note note) {
    helperMethods.throwIfIdMismatch(
        noteCollectionId,
        note.getNotecollection().getId(),
        new MismatchingIdException(
            "Note is not a member of the Note-Collection specified in the path variable"));
  }
}
