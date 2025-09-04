package com.bella.IW2BR.domain.environment;

import com.bella.IW2BR.domain.environment.dto.GetEnvironment;
import com.bella.IW2BR.domain.environment.dto.PatchEnvironment;
import com.bella.IW2BR.domain.environment.dto.PostEnvironment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface EnvironmentControllerDocs {
  @Operation(
      summary = "create environment",
      description = "creates a new environment and saves it in the database")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(responseCode = "400", description = "Invalid request body"),
    @ApiResponse(responseCode = "400", description = "Environment already present")
  })
  public ResponseEntity<GetEnvironment> create(PostEnvironment body);

  @Operation(summary = "get environment by id", description = "finds environment by id ")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(responseCode = "403", description = "no permission to access this resource"),
    @ApiResponse(responseCode = "404", description = "environment not found")
  })
  public ResponseEntity<?> get(Long id);

  @Operation(
      summary = "get all environments",
      description =
          "Returns owned environments for the authenticated user, if ADMIN is logged in all environments are returned")
  @ApiResponses({
    @ApiResponse(responseCode = "403", description = "no permission to access this resource"),
    @ApiResponse(responseCode = "404", description = "environment not found")
  })
  public ResponseEntity<List<GetEnvironment>> getAll();

  @Operation(
      summary = "update environment",
      description = "updates environment and saves it in de database")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(responseCode = "400", description = "invalid request body"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner or ADMIN"),
    @ApiResponse(responseCode = "404", description = "environment not found")
  })
  public ResponseEntity<GetEnvironment> patch(Long id, PatchEnvironment patch);

  @Operation(summary = "delete environment", description = "deletes an environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "success"),
    @ApiResponse(
        responseCode = "403",
        description = "authenticated user is not the owner or ADMIN"),
    @ApiResponse(responseCode = "404", description = "environment not found")
  })
  public ResponseEntity<Void> delete(Long id);
}
