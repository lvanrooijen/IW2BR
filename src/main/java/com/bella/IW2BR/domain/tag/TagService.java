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
    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    Environment environment = environmentHelperMethods.getEnvironmentOrThrow(environmentId);

    Tag tag = PostTag.from(body, environment);

    tagRepository.save(tag);
    return GetTag.to(tag);
  }

  public GetTag getById(Long environmentId, Long id) {
    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Tag tag = environmentHelperMethods.getTagOrThrow(id);
    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);

    return GetTag.to(tag);
  }

  public List<GetTag> getAllTags(Long environmentId) {
    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    List<Tag> tags = tagRepository.findAllByEnvironmentId(environmentId);
    if (tags.isEmpty()) {
      return new ArrayList<>();
    }

    return tags.stream().map(GetTag::to).toList();
  }

  public GetTag update(Long environmentId, Long id, PatchTag patch) {
    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Tag tag = environmentHelperMethods.getTagOrThrow(id);
    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);

    PatchTag.patch(tag, patch);

    tagRepository.save(tag);
    return GetTag.to(tag);
  }

  public void delete(Long environmentId, Long id) {
    environmentHelperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Tag tag = environmentHelperMethods.getTagOrThrow(id);
    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);

    tagRepository.deleteById(id);
  }
}
