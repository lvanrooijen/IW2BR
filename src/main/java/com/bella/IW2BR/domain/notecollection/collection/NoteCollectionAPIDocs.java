package com.bella.IW2BR.domain.notecollection.collection;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.notecollection.collection.dto.GetNoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.dto.PatchNoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.dto.PostNoteCollection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface NoteCollectionAPIDocs {

  @Operation(summary = "Create Note Collection")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(responseCode = "400", description = INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<GetNoteCollection> create(Long environmentId, PostNoteCollection body);

  @Operation(summary = "get Note Collection by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "400", description = NOTE_COLLECTION_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + NOTE_COLLECTION_404),
  })
  public ResponseEntity<GetNoteCollection> get(Long environmentId, Long id);

  @Operation(summary = "Get all Note Collections")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<List<GetNoteCollection>> getAll(Long environmentId);

  @Operation(summary = "Patch Note Collection")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description = INVALID_REQUEST_BODY + SEPARATOR + NOTE_COLLECTION_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + NOTE_COLLECTION_404),
  })
  public ResponseEntity<GetNoteCollection> patch(
      Long environmentId, Long id, PatchNoteCollection patch);

  @Operation(
      summary = "Delete Note Collection",
      description = "Deletes note collection and all notes connected to it")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(responseCode = "400", description = NOTE_COLLECTION_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(
        responseCode = "404",
        description = ENVIRONMENT_404 + SEPARATOR + NOTE_COLLECTION_404),
  })
  public ResponseEntity<Void> delete(Long environmentId, Long id);
}
