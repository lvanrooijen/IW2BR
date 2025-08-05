package com.bella.IW2BR.domain.notecollection;

import com.bella.IW2BR.domain.notecollection.dto.GetNoteCollection;
import com.bella.IW2BR.domain.notecollection.dto.PatchNoteCollection;
import com.bella.IW2BR.domain.notecollection.dto.PostNoteCollection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface NoteCollectionControllerDocs {

  @Operation(
      summary = "Create note collection",
      description = "Creates a new note collection in the given environment")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Note collection created"),
    @ApiResponse(
        responseCode = "400",
        description = "Invalid request body or collection not in environment"),
    @ApiResponse(
        responseCode = "403",
        description = "User is not the owner of the environment or an admin"),
    @ApiResponse(responseCode = "404", description = "Environment not found")
  })
  @PostMapping
  ResponseEntity<GetNoteCollection> createNoteCollection(
      @PathVariable Long environmentId, @RequestBody PostNoteCollection body);

  @Operation(
      summary = "Get note collection by ID",
      description = "Returns a specific note collection by ID within the given environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Note collection returned"),
    @ApiResponse(responseCode = "400", description = "Note collection not in environment"),
    @ApiResponse(
        responseCode = "403",
        description = "User is not the owner of the environment or an admin"),
    @ApiResponse(responseCode = "404", description = "Note collection not found")
  })
  @GetMapping("/{id}")
  ResponseEntity<GetNoteCollection> getNoteCollectionById(
      @PathVariable Long environmentId, @PathVariable Long id);

  @Operation(
      summary = "Get all note collections",
      description = "Returns a list of all note collections in the given environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Note collections returned"),
    @ApiResponse(
        responseCode = "403",
        description = "User is not the owner of the environment or an admin"),
    @ApiResponse(responseCode = "404", description = "Environment not found")
  })
  @GetMapping
  ResponseEntity<List<GetNoteCollection>> getAllNoteCollections(@PathVariable Long environmentId);

  @Operation(
      summary = "Update note collection",
      description = "Updates note collection in the given environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Note collection updated"),
    @ApiResponse(
        responseCode = "400",
        description = "Invalid request body or note collection not in environment"),
    @ApiResponse(
        responseCode = "403",
        description = "User is not the owner of the environment or an admin"),
    @ApiResponse(responseCode = "404", description = "Note collection not found")
  })
  @PatchMapping("/{id}")
  ResponseEntity<GetNoteCollection> updateNoteCollection(
      @PathVariable Long environmentId,
      @PathVariable Long id,
      @RequestBody PatchNoteCollection body);

  @Operation(
      summary = "Delete note collection",
      description = "Deletes a note collection and all related notes from the given environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Note collection deleted"),
    @ApiResponse(responseCode = "400", description = "Note collection not in environment"),
    @ApiResponse(
        responseCode = "403",
        description = "User is not the owner of the environment or an admin"),
    @ApiResponse(responseCode = "404", description = "Note collection not found")
  })
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteNoteCollection(
      @PathVariable Long environmentId, @PathVariable Long id);
}
