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

@RestController
@RequestMapping(Endpoints.TAGS)
@RequiredArgsConstructor
public class TagController {
  private final TagService tagService;

  @PostMapping
  public ResponseEntity<GetTag> createTag(@RequestBody @Valid PostTag body) {
    GetTag tag = tagService.createTag(body);
    URI location =
        UriComponentsBuilder.newInstance().path("tags/{id}").buildAndExpand(tag.id()).toUri();
    return ResponseEntity.created(location).body(tag);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetTag> getTagById(@PathVariable Long id) {
    GetTag tag = tagService.getTagById(id);
    return ResponseEntity.ok(tag);
  }

  @GetMapping
  public ResponseEntity<List<GetTag>> getAllTags() {
    List<GetTag> tags = tagService.getAllTags();
    return ResponseEntity.ok(tags);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GetTag> updateTag(@PathVariable Long id, @RequestBody PatchTag patch) {
    GetTag tag = tagService.updateTag(id, patch);
    return ResponseEntity.ok(tag);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
    tagService.deleteTag(id);
    return ResponseEntity.ok().build();
  }
}
