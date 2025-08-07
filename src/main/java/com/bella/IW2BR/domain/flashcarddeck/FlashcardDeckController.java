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
  public ResponseEntity<GetFlashcardDeck> createFlashcardDeck(
      @PathVariable("environmentId") Long environmentId,
      @Valid @RequestBody PostFlashcardDeck body) {
    GetFlashcardDeck flashcardDeck = flashcardDeckService.createFlashcardDeck(environmentId, body);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(flashcardDeck.id())
            .toUri();

    return ResponseEntity.created(location).body(flashcardDeck);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetFlashcardDeck> getFlashcardDeckById(
      @PathVariable Long environmentId, @PathVariable Long id) {
    GetFlashcardDeck flashcardDeck = flashcardDeckService.getFlashcardDeckById(environmentId, id);
    return ResponseEntity.ok(flashcardDeck);
  }

  @GetMapping
  public ResponseEntity<List<GetFlashcardDeck>> getAllFlashcardDecks(
      @PathVariable Long environmentId) {
    List<GetFlashcardDeck> flashcardDecks =
        flashcardDeckService.getAllFlashcardDecks(environmentId);
    return ResponseEntity.ok(flashcardDecks);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetFlashcardDeck> updateFlashcardDeck(
      @PathVariable Long environmentId,
      @PathVariable Long id,
      @RequestBody PatchFlashcardDeck patch) {
    GetFlashcardDeck flashcardDeck =
        flashcardDeckService.updateFlashcardDeck(environmentId, id, patch);
    return ResponseEntity.ok(flashcardDeck);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFlashcardDeck(
      @PathVariable Long environmentId, @PathVariable Long id) {
    flashcardDeckService.deleteFlashcardDeck(environmentId, id);
    return ResponseEntity.ok().build();
  }
}
