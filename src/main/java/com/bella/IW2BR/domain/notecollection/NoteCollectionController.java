package com.bella.IW2BR.domain.notecollection;

import com.bella.IW2BR.domain.notecollection.dto.GetNoteCollection;
import com.bella.IW2BR.domain.notecollection.dto.PatchNoteCollection;
import com.bella.IW2BR.domain.notecollection.dto.PostNoteCollection;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller for handling note collection related operations.
 *
 * <p>The TAGS endpoint contains an environmentId path variable:
 * /environments/{environmentId}/note_collections
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.NOTE_COLLECTIONS)
public class NoteCollectionController implements NoteCollectionControllerDocs {
  private final NoteCollectionService noteCollectionService;

  @Override
  @PostMapping
  public ResponseEntity<GetNoteCollection> createNoteCollection(
      @PathVariable Long environmentId, @RequestBody PostNoteCollection body) {
    GetNoteCollection noteCollection =
        noteCollectionService.createNoteCollection(environmentId, body);

    URI location =
        UriComponentsBuilder.newInstance()
            .path("/environments/{environmentId}/note_collections/{id}")
            .buildAndExpand(environmentId, noteCollection.id())
            .toUri();
    return ResponseEntity.created(location).body(noteCollection);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<GetNoteCollection> getNoteCollectionById(
      @PathVariable Long environmentId, @PathVariable Long id) {
    GetNoteCollection noteCollection =
        noteCollectionService.getNoteCollectionById(environmentId, id);
    return ResponseEntity.ok(noteCollection);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<GetNoteCollection>> getAllNoteCollections(
      @PathVariable Long environmentId) {
    List<GetNoteCollection> noteCollections =
        noteCollectionService.getAllNoteCollections(environmentId);
    return ResponseEntity.ok(noteCollections);
  }

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<GetNoteCollection> updateNoteCollection(
      @PathVariable Long environmentId,
      @PathVariable Long id,
      @RequestBody PatchNoteCollection patch) {
    GetNoteCollection noteCollection =
        noteCollectionService.updateNoteCollection(environmentId, id, patch);
    return ResponseEntity.ok(noteCollection);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteNoteCollection(
      @PathVariable Long environmentId, @PathVariable Long id) {
    noteCollectionService.deleteCollection(environmentId, id);
    return ResponseEntity.ok().build();
  }
}
