package com.bella.IW2BR.domain.environment;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.environment.dto.GetEnvironment;
import com.bella.IW2BR.domain.environment.dto.PatchEnvironment;
import com.bella.IW2BR.domain.environment.dto.PostEnvironment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface EnvironmentAPIDocs {
  @Operation(summary = "create environment")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(
        responseCode = "400",
        description = INVALID_REQUEST_BODY + SEPARATOR + DUPLICATE_ENVIRONMENT)
  })
  public ResponseEntity<GetEnvironment> create(PostEnvironment body);

  @Operation(summary = "get environment by ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<?> get(Long id);

  @Operation(
      summary = "get all environments",
      description =
          "Returns owned environments for the authenticated user, if ADMIN is logged in all environments are returned")
  @ApiResponses({
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<List<GetEnvironment>> getAll();

  @Operation(summary = "patch environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "400", description = INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<GetEnvironment> patch(Long id, PatchEnvironment patch);

  @Operation(summary = "delete environment")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(responseCode = "400", description = INVALID_REQUEST_BODY),
    @ApiResponse(responseCode = "403", description = OWNER_ADMIN_ACCESS_ONLY),
    @ApiResponse(responseCode = "404", description = ENVIRONMENT_404)
  })
  public ResponseEntity<Void> delete(Long id);
}
