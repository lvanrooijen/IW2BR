package com.bella.IW2BR.domain.notecollection.note;

import com.bella.IW2BR.domain.notecollection.note.dto.GetNote;
import com.bella.IW2BR.domain.notecollection.note.dto.PatchNote;
import com.bella.IW2BR.domain.notecollection.note.dto.PostNote;
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
  public ResponseEntity<GetNote> create(
      @PathVariable Long environmentId,
      @PathVariable Long noteCollectionId,
      @RequestBody @Valid PostNote body) {
    GetNote note = noteService.create(environmentId, noteCollectionId, body);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(note.id())
            .toUri();
    return ResponseEntity.created(location).body(note);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetNote> get(
      @PathVariable Long environmentId,
      @PathVariable Long noteCollectionId,
      @PathVariable Long id) {
    GetNote note = noteService.getById(environmentId, noteCollectionId, id);
    return ResponseEntity.ok(note);
  }

  @GetMapping
  public ResponseEntity<List<GetNote>> getAll(
      @PathVariable Long environmentId, @PathVariable Long noteCollectionId) {
    List<GetNote> notes = noteService.getAll(environmentId, noteCollectionId);
    return ResponseEntity.ok(notes);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetNote> patch(
      @PathVariable Long environmentId,
      @PathVariable Long noteCollectionId,
      @PathVariable Long id,
      @RequestBody @Valid PatchNote patch) {
    GetNote note = noteService.update(environmentId, noteCollectionId, id, patch);
    return ResponseEntity.ok(note);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable Long environmentId,
      @PathVariable Long noteCollectionId,
      @PathVariable Long id) {
    noteService.delete(environmentId, noteCollectionId, id);
    return ResponseEntity.noContent().build();
  }
}
