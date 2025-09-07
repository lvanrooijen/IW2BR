package com.bella.IW2BR.domain.notecollection.note;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.notecollection.note.dto.GetNote;
import com.bella.IW2BR.domain.notecollection.note.dto.PatchNote;
import com.bella.IW2BR.domain.notecollection.note.dto.PostNote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface NoteAPIDocs {
  @Operation(summary = "Create Note")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(
        responseCode = "400",
        description = INVALID_REQUEST_BODY + SEPARATOR + NOTE_COLLECTION_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + NOTE_COLLECTION_404 + SEPARATOR + TAG_404)
  })
  public ResponseEntity<GetNote> create(Long environmentId, Long noteCollectionId, PostNote body);

  @Operation(summary = "get Note by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description = NOTE_NOT_IN_COLLECTION + SEPARATOR + NOTE_COLLECTION_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + NOTE_COLLECTION_404 + SEPARATOR + NOTE_404),
  })
  public ResponseEntity<GetNote> get(Long environmentId, Long noteCollectionId, Long id);

  @Operation(
      summary = "Get all notes",
      description = "gets all notes connected to the specified note collection")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + NOTE_COLLECTION_404)
  })
  public ResponseEntity<List<GetNote>> getAll(Long environmentId, Long noteCollectionId);

  @Operation(summary = "Patch Note")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description = NOTE_NOT_IN_COLLECTION + SEPARATOR + INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + NOTE_404 + SEPARATOR + TAG_404),
  })
  public ResponseEntity<GetNote> patch(
      Long environmentId, Long noteCollectionId, Long id, PatchNote patch);

  @Operation(summary = "Delete Note")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(
        responseCode = "400",
        description = NOTE_COLLECTION_NOT_IN_ENVIRONMENT + SEPARATOR + NOTE_NOT_IN_COLLECTION),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + NOTE_COLLECTION_404 + SEPARATOR + NOTE_404),
  })
  public ResponseEntity<Void> delete(Long environmentId, Long noteCollectionId, Long id);
}
