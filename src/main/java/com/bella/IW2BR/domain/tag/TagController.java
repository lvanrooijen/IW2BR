package com.bella.IW2BR.domain.tag;

import com.bella.IW2BR.domain.tag.dto.GetTag;
import com.bella.IW2BR.domain.tag.dto.PatchTag;
import com.bella.IW2BR.domain.tag.dto.PostTag;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller for handling tag related operations.
 *
 * <p>The TAGS endpoint contains an environmentId path variable: /environments/{environmentId}/tags
 */
@RestController
@RequestMapping(Endpoints.TAGS)
@RequiredArgsConstructor
public class TagController implements TagControllerDocs {
  private final TagService tagService;

  @Override
  @PostMapping
  public ResponseEntity<GetTag> createTag(
      @PathVariable Long environmentId, @RequestBody @Valid PostTag body) {
    GetTag tag = tagService.createTag(environmentId, body);
    URI location =
        UriComponentsBuilder.newInstance()
            .path("/environments/{environmentId}/tags/{id}")
            .buildAndExpand(environmentId, tag.id())
            .toUri();
    return ResponseEntity.created(location).body(tag);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<GetTag> getTagById(
      @PathVariable Long environmentId, @PathVariable Long id) {
    GetTag tag = tagService.getTagById(environmentId, id);
    return ResponseEntity.ok(tag);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<GetTag>> getAllTags(@PathVariable Long environmentId) {
    List<GetTag> tags = tagService.getAllTags(environmentId);
    return ResponseEntity.ok(tags);
  }

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<GetTag> updateTag(
      @PathVariable Long environmentId, @PathVariable Long id, @RequestBody PatchTag patch) {
    GetTag tag = tagService.updateTag(environmentId, id, patch);
    return ResponseEntity.ok(tag);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTag(@PathVariable Long environmentId, @PathVariable Long id) {
    tagService.deleteTag(environmentId, id);
    return ResponseEntity.ok().build();
  }
}
