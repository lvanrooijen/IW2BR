package com.bella.IW2BR.domain.tag;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.tag.dto.GetTag;
import com.bella.IW2BR.domain.tag.dto.PatchTag;
import com.bella.IW2BR.domain.tag.dto.PostTag;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** Handles the business logic of tags */
@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {
  private final TagRepository tagRepository;
  private final EnvironmentHelperMethods environmentHelperMethods;

  public GetTag create(Long environmentId, PostTag body) {
    Environment environment = environmentHelperMethods.getEnvironmentOrThrow(environmentId);

    Tag tag = PostTag.from(body, environment);

    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);

    tagRepository.save(tag);
    return GetTag.to(tag);
  }

  public void delete(Long environmentId, Long id) {
    Tag tag = environmentHelperMethods.getTagOrThrow(id);

    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);
    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(
        tag.getEnvironment().getId());

    tagRepository.deleteById(id);
  }

  public GetTag getById(Long environmentId, Long id) {
    Tag tag = environmentHelperMethods.getTagOrThrow(id);

    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);
    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(
        tag.getEnvironment().getId());

    return GetTag.to(tag);
  }

  public List<GetTag> getAllTags(Long environmentId) {
    List<Tag> tags = tagRepository.findAllByEnvironmentId(environmentId);
    if (tags.isEmpty()) {
      return new ArrayList<>();
    }

    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(
        tags.get(0).getEnvironment().getId());

    return tags.stream().map(GetTag::to).toList();
  }

  public GetTag update(Long environmentId, Long id, PatchTag patch) {
    Tag tag = environmentHelperMethods.getTagOrThrow(id);

    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);

    PatchTag.patch(tag, patch);

    tagRepository.save(tag);
    return GetTag.to(tag);
  }
}
