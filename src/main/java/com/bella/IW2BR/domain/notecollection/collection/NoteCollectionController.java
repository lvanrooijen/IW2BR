package com.bella.IW2BR.domain.notecollection.collection;

import com.bella.IW2BR.domain.notecollection.collection.dto.GetNoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.dto.PatchNoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.dto.PostNoteCollection;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller for handling note collection related operations.
 *
 * <p>The NOTE COLLECTIONS endpoint contains an environmentId path variable:
 * /environments/{environmentId}/note_collections
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.NOTE_COLLECTIONS)
public class NoteCollectionController implements NoteCollectionControllerDocs {
  private final NoteCollectionService noteCollectionService;

  @Override
  @PostMapping
  public ResponseEntity<GetNoteCollection> create(
      @PathVariable Long environmentId, @Valid @RequestBody PostNoteCollection body) {
    GetNoteCollection noteCollection = noteCollectionService.create(environmentId, body);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(noteCollection.id())
            .toUri();
    return ResponseEntity.created(location).body(noteCollection);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<GetNoteCollection> get(
      @PathVariable Long environmentId, @PathVariable Long id) {
    GetNoteCollection noteCollection = noteCollectionService.getById(environmentId, id);
    return ResponseEntity.ok(noteCollection);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<GetNoteCollection>> getAll(@PathVariable Long environmentId) {
    List<GetNoteCollection> noteCollections = noteCollectionService.getAll(environmentId);
    return ResponseEntity.ok(noteCollections);
  }

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<GetNoteCollection> patch(
      @PathVariable Long environmentId,
      @PathVariable Long id,
      @Valid @RequestBody PatchNoteCollection patch) {
    GetNoteCollection noteCollection = noteCollectionService.update(environmentId, id, patch);
    return ResponseEntity.ok(noteCollection);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long environmentId, @PathVariable Long id) {
    noteCollectionService.delete(environmentId, id);
    return ResponseEntity.noContent().build();
  }
}
