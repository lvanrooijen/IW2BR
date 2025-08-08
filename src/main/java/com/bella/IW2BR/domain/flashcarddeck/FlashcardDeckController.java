package com.bella.IW2BR.domain.flashcarddeck;

import com.bella.IW2BR.domain.flashcarddeck.dto.GetFlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.dto.PatchFlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.dto.PostFlashcardDeck;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Controller for handling flashcard deck related operations.
 *
 * <p>The FLASHCARD_DECKS endpoint contains an environmentId path variable:
 * /environments/{environmentId}/flashcard_decks
 */
@RestController
@RequestMapping(Endpoints.FLASHCARD_DECKS)
@RequiredArgsConstructor
public class FlashcardDeckController {
  private final FlashcardDeckService flashcardDeckService;

  @PostMapping
  public ResponseEntity<GetFlashcardDeck> create(
      @PathVariable("environmentId") Long environmentId,
      @Valid @RequestBody PostFlashcardDeck body) {
    GetFlashcardDeck flashcardDeck = flashcardDeckService.create(environmentId, body);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(flashcardDeck.id())
            .toUri();

    return ResponseEntity.created(location).body(flashcardDeck);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetFlashcardDeck> get(
      @PathVariable Long environmentId, @PathVariable Long id) {
    GetFlashcardDeck flashcardDeck = flashcardDeckService.getById(environmentId, id);
    return ResponseEntity.ok(flashcardDeck);
  }

  @GetMapping
  public ResponseEntity<List<GetFlashcardDeck>> getAll(@PathVariable Long environmentId) {
    List<GetFlashcardDeck> flashcardDecks = flashcardDeckService.getAll(environmentId);
    return ResponseEntity.ok(flashcardDecks);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetFlashcardDeck> patch(
      @PathVariable Long environmentId,
      @PathVariable Long id,
      @RequestBody PatchFlashcardDeck patch) {
    GetFlashcardDeck flashcardDeck = flashcardDeckService.update(environmentId, id, patch);
    return ResponseEntity.ok(flashcardDeck);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long environmentId, @PathVariable Long id) {
    flashcardDeckService.delete(environmentId, id);
    return ResponseEntity.ok().build();
  }
}
