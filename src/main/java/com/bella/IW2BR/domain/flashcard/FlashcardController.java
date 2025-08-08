package com.bella.IW2BR.domain.flashcard;

import com.bella.IW2BR.domain.flashcard.dto.GetFlashcard;
import com.bella.IW2BR.domain.flashcard.dto.PatchFlashcard;
import com.bella.IW2BR.domain.flashcard.dto.PostFlashcard;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller for handling flashcard related operations.
 *
 * <p>The FLASHCARD endpoint contains an environmentId and flashcardDeckId path variable:
 * environments/{environmentId}/flashcard_decks/{flashcardDeckId}/flashcards
 */
@RestController
@RequestMapping(Endpoints.FLASHCARDS)
@RequiredArgsConstructor
public class FlashcardController {
  private final FlashcardService flashcardService;

  @PostMapping
  public ResponseEntity<GetFlashcard> create(
      @PathVariable Long environmentId,
      @PathVariable Long flashcardDeckId,
      @RequestBody PostFlashcard body) {
    GetFlashcard flashcard = flashcardService.create(environmentId, flashcardDeckId, body);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(flashcard.id())
            .toUri();
    return ResponseEntity.created(location).body(flashcard);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetFlashcard> get(
      @PathVariable Long environmentId, @PathVariable Long flashcardDeckId, @PathVariable Long id) {
    GetFlashcard flashcard = flashcardService.getById(environmentId, flashcardDeckId, id);
    return ResponseEntity.ok(flashcard);
  }

  @GetMapping
  public ResponseEntity<List<GetFlashcard>> getAll(
      @PathVariable Long environmentId, @PathVariable Long flashcardDeckId) {
    List<GetFlashcard> flashcards = flashcardService.getAll(environmentId, flashcardDeckId);

    return ResponseEntity.ok(flashcards);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetFlashcard> patch(
      @PathVariable Long environmentId,
      @PathVariable Long flashcardDeckId,
      @PathVariable Long id,
      @RequestBody PatchFlashcard patch) {
    GetFlashcard flashcard = flashcardService.update(environmentId, flashcardDeckId, id, patch);
    return ResponseEntity.ok(flashcard);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @PathVariable Long environmentId, @PathVariable Long flashcardDeckId, @PathVariable Long id) {
    flashcardService.delete(environmentId, flashcardDeckId, id);
    return ResponseEntity.ok().build();
  }
}
