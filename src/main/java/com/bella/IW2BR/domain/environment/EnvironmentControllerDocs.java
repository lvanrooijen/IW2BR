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
  @ApiResponse(
      responseCode = "400",
      description = "invalid request body, environment already present")
  public ResponseEntity<GetEnvironment> create(PostEnvironment body);

  @Operation(summary = "get environment by id", description = "finds environment by id ")
  @ApiResponses({
    @ApiResponse(responseCode = "403", description = "no permission to access this resource"),
    @ApiResponse(responseCode = "404", description = "environment not found")
  })
  public ResponseEntity<GetEnvironment> get(Long id);

  @Operation(summary = "get all environments", description = "get all environments")
  @ApiResponses({
    @ApiResponse(responseCode = "403", description = "no permission to access this resource"),
    @ApiResponse(responseCode = "404", description = "environment not found")
  })
  public ResponseEntity<List<GetEnvironment>> getAll();

  @Operation(
      summary = "update environment",
      description = "updates environment and saves it in de database")
  @ApiResponses({
    @ApiResponse(responseCode = "400", description = "invalid request body"),
    @ApiResponse(responseCode = "404", description = "environment not found")
  })
  public ResponseEntity<GetEnvironment> patch(Long id, PatchEnvironment patch);

  @Operation(summary = "delete environment", description = "deletes an environment")
  @ApiResponses({
    @ApiResponse(
        responseCode = "403",
        description = "only owner of admin can delete this resource"),
    @ApiResponse(responseCode = "404", description = "environment nog found")
  })
  public ResponseEntity<Void> delete(Long id);
}
