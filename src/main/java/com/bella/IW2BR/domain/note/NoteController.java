package com.bella.IW2BR.domain.note;

import com.bella.IW2BR.domain.note.dto.GetNote;
import com.bella.IW2BR.domain.note.dto.PatchNote;
import com.bella.IW2BR.domain.note.dto.PostNote;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller for handling note related operations.
 *
 * <p>The NOTES endpoint contains an environmentId and a noteCollectionId path variable:
 * /environments/{environmentId}/note_collections/{noteCollectionId}/notes
 */
@RestController
@RequestMapping(Endpoints.NOTES)
@RequiredArgsConstructor
public class NoteController {
  private final NoteService noteService;

  @PostMapping
  public ResponseEntity<GetNote> createNote(
      @PathVariable Long environmentId,
      @PathVariable Long noteCollectionId,
      @RequestBody @Valid PostNote body) {
    GetNote note = noteService.createNote(environmentId, noteCollectionId, body);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(note.id())
            .toUri();
    return ResponseEntity.created(location).body(note);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetNote> getNoteById(
      @PathVariable Long environmentId,
      @PathVariable Long noteCollectionId,
      @PathVariable Long id) {
    GetNote note = noteService.getNoteById(environmentId, noteCollectionId, id);
    return ResponseEntity.ok(note);
  }

  @GetMapping
  public ResponseEntity<List<GetNote>> getAllNotes(
      @PathVariable Long environmentId, @PathVariable Long noteCollectionId) {
    List<GetNote> notes = noteService.getAllNotes(environmentId, noteCollectionId);
    return ResponseEntity.ok(notes);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetNote> updateNote(
      @PathVariable Long environmentId,
      @PathVariable Long noteCollectionId,
      @PathVariable Long id,
      @RequestBody @Valid PatchNote patch) {
    GetNote note = noteService.updateNote(environmentId, noteCollectionId, id, patch);
    return ResponseEntity.ok(note);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNote(
      @PathVariable Long environmentId,
      @PathVariable Long noteCollectionId,
      @PathVariable Long id) {
    noteService.deleteNote(environmentId, noteCollectionId, id);
    return ResponseEntity.ok().build();
  }
}
