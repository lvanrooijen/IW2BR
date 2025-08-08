package com.bella.IW2BR.domain.tag;

import com.bella.IW2BR.domain.tag.dto.GetTag;
import com.bella.IW2BR.domain.tag.dto.PatchTag;
import com.bella.IW2BR.domain.tag.dto.PostTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TagControllerDocs {

  @Operation(summary = "Create new tag", description = "Creates new tag in the given environment")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Tag successfully created"),
    @ApiResponse(
        responseCode = "400",
        description = "Invalid request body or tag does not belong to environment"),
    @ApiResponse(
        responseCode = "403",
        description = "User is not the owner of the environment or an admin"),
    @ApiResponse(responseCode = "404", description = "Environment not found")
  })
  ResponseEntity<GetTag> create(@PathVariable Long environmentId, @RequestBody PostTag body);

  @Operation(
      summary = "Get tag by ID",
      description = "Gets a tag by its ID from the given environment.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Tag found"),
    @ApiResponse(responseCode = "400", description = "Tag does not belong to environment"),
    @ApiResponse(
        responseCode = "403",
        description = "User is not the owner of the environment or an admin"),
    @ApiResponse(responseCode = "404", description = "Tag not found")
  })
  ResponseEntity<GetTag> get(@PathVariable Long environmentId, @RequestBody Long id);

  @Operation(summary = "Get all tags", description = "Returns all tags from the given environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "List of tags returned"),
    @ApiResponse(responseCode = "404", description = "Environment not found")
  })
  ResponseEntity<List<GetTag>> getAll(@PathVariable Long environmentId);

  @Operation(
      summary = "Update tag",
      description =
          "Updates the title, description, or flags the status of a tag (positively or negatively)")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Tag successfully updated"),
    @ApiResponse(
        responseCode = "400",
        description = "Invalid request body or tag does not belong to environment"),
    @ApiResponse(
        responseCode = "403",
        description = "User is not the owner of the environment or an admin"),
    @ApiResponse(responseCode = "404", description = "Tag not found")
  })
  ResponseEntity<GetTag> patch(
      @PathVariable Long environmentId, @PathVariable Long id, @RequestBody PatchTag patch);

  @Operation(summary = "Delete tag", description = "Deletes tag from the environment")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Tag deleted"),
    @ApiResponse(responseCode = "400", description = "Tag does not belong to environment"),
    @ApiResponse(
        responseCode = "403",
        description = "User is not the owner of the environment or an admin"),
    @ApiResponse(responseCode = "404", description = "Tag not found")
  })
  ResponseEntity<Void> delete(@PathVariable Long environmentId, @PathVariable Long id);
}
