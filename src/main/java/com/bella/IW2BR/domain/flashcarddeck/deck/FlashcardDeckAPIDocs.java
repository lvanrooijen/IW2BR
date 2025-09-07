package com.bella.IW2BR.domain.flashcarddeck.deck;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.flashcarddeck.deck.dto.GetFlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.deck.dto.PatchFlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.deck.dto.PostFlashcardDeck;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface FlashcardDeckAPIDocs {
  @Operation(summary = "create flashcard deck")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(responseCode = "400", description = INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<GetFlashcardDeck> create(Long environmentId, PostFlashcardDeck body);

  @Operation(summary = "get flashcard deck by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "400", description = FLASHCARD_DECK_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + FLASHCARD_DECK_404),
  })
  public ResponseEntity<GetFlashcardDeck> get(Long environmentId, Long id);

  @Operation(summary = "Get all Flashcard Decks")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<List<GetFlashcardDeck>> getAll(Long environmentId);

  @Operation(summary = "patch flashcard deck")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description = INVALID_REQUEST_BODY + SEPARATOR + FLASHCARD_DECK_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + FLASHCARD_DECK_404),
  })
  public ResponseEntity<GetFlashcardDeck> patch(
      Long environmentId, Long id, PatchFlashcardDeck patch);

  @Operation(summary = "delete flashcard deck")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(responseCode = "400", description = INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + FLASHCARD_DECK_404)
  })
  public ResponseEntity<Void> delete(Long environmentId, Long id);
}
