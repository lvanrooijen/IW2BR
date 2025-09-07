package com.bella.IW2BR.domain.flashcarddeck.flashcard;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.GetFlashcard;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.PatchFlashcard;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.PostFlashcard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface FlashcardAPIDocs {
  @Operation(summary = "Create Flashcard")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(
        responseCode = "400",
        description = INVALID_REQUEST_BODY + SEPARATOR + FLASHCARD_DECK_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + FLASHCARD_DECK_404 + SEPARATOR + TAG_404)
  })
  public ResponseEntity<GetFlashcard> create(
      Long environmentId, Long flashcardDeckId, PostFlashcard body);

  @Operation(summary = "get Flashcard by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description = FLASHCARD_NOT_IN_DECK + SEPARATOR + FLASHCARD_DECK_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404 + SEPARATOR + FLASHCARD_404),
  })
  public ResponseEntity<GetFlashcard> get(Long environmentId, Long flashcardDeckId, Long id);

  @Operation(summary = "Get All Flashcards")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "400", description = FLASHCARD_DECK_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + FLASHCARD_DECK_404),
  })
  public ResponseEntity<List<GetFlashcard>> getAll(Long environmentId, Long flashcardDeckId);

  @Operation(summary = "Patch Flashcard")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description = FLASHCARD_NOT_IN_DECK + SEPARATOR + FLASHCARD_DECK_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + FLASHCARD_404 + SEPARATOR + TAG_404),
  })
  public ResponseEntity<GetFlashcard> patch(
      Long environmentId, Long flashcardDeckId, Long id, PatchFlashcard patch);

  @Operation(summary = "Delete Flashcard")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(
        responseCode = "400",
        description = FLASHCARD_NOT_IN_DECK + SEPARATOR + FLASHCARD_DECK_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404 + SEPARATOR + FLASHCARD_404),
  })
  public ResponseEntity<Void> delete(Long environmentId, Long flashcardDeckId, Long id);
}
