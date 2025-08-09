package com.bella.IW2BR.domain.notecollection.note;

import com.bella.IW2BR.domain.notecollection.note.dto.GetNote;
import com.bella.IW2BR.domain.notecollection.note.dto.PatchNote;
import com.bella.IW2BR.domain.notecollection.note.dto.PostNote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface NoteControllerSwaggerDocs {
  @Operation(summary = "Creates a new note")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Note created"),
    @ApiResponse(
        responseCode = "400",
        description =
            "Invalid request body, provided note collection is not part of the environment"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner of the environment or admin"),
    @ApiResponse(responseCode = "404", description = "Note collection or environment not found")
  })
  ResponseEntity<GetNote> createNote(Long environmentId, Long noteCollectionId, PostNote body);

  @Operation(summary = "Gets note by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Note found"),
    @ApiResponse(
        responseCode = "400",
        description = "provided note collection is not part of the environment"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner of the environment or admin"),
    @ApiResponse(responseCode = "404", description = "Note or note collection not found")
  })
  ResponseEntity<GetNote> getNoteById(Long environmentId, Long noteCollectionId, Long id);

  @Operation(summary = "Gets all notes in a note collection")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Notes returned"),
    @ApiResponse(
        responseCode = "400",
        description = "provided note collection is not part of the environment"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner of the environment or admin"),
    @ApiResponse(responseCode = "404", description = "Note Collection not found")
  })
  ResponseEntity<List<GetNote>> getAllNotes(Long environmentId, Long noteCollectionId);

  @Operation(summary = "Update a note")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Note updated"),
    @ApiResponse(responseCode = "400", description = "Invalid patch body"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner of the environment or admin"),
    @ApiResponse(responseCode = "404", description = "Note not found")
  })
  ResponseEntity<GetNote> updateNote(
      Long environmentId, Long noteCollectionId, Long id, PatchNote patch);

  @Operation(summary = "Delete a note")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Note deleted"),
    @ApiResponse(
        responseCode = "400",
        description = "provided note collection is not part of the environment"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner of the environment or admin"),
    @ApiResponse(responseCode = "404", description = "Note or tag not found")
  })
  ResponseEntity<Void> deleteNote(Long environmentId, Long noteCollectionId, Long id);
}
