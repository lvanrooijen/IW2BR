package com.bella.IW2BR.domain.tag;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.tag.dto.GetTag;
import com.bella.IW2BR.domain.tag.dto.PatchTag;
import com.bella.IW2BR.domain.tag.dto.PostTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TagControllerDocs {
  @Operation(summary = "Create Tag")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(responseCode = "400", description = INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404),
  })
  public ResponseEntity<GetTag> create(Long environmentId, PostTag body);

  @Operation(summary = "Get Tag by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "400", description = TAG_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404 + SEPARATOR + TAG_404),
  })
  public ResponseEntity<GetTag> get(Long environmentId, Long id);

  @Operation(
      summary = "Get all Tags",
      description = "Get all tags related to the specified environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<List<GetTag>> getAll(Long environmentId);

  @Operation(summary = "Patch Tag")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "400",
        description = INVALID_REQUEST_BODY + SEPARATOR + TAG_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404 + SEPARATOR + TAG_404),
  })
  public ResponseEntity<GetTag> patch(Long environmentId, Long id, PatchTag patch);

  @Operation(summary = "Delete Tag")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(responseCode = "400", description = TAG_NOT_IN_ENVIRONMENT),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404 + SEPARATOR + TAG_404),
  })
  public ResponseEntity<Void> delete(Long environmentId, Long id);
}
