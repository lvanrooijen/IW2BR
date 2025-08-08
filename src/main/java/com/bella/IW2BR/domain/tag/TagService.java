package com.bella.IW2BR.domain.tag;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.tag.dto.GetTag;
import com.bella.IW2BR.domain.tag.dto.PatchTag;
import com.bella.IW2BR.domain.tag.dto.PostTag;
import com.bella.IW2BR.domain.tag.dto.TagMapper;
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
  private final TagMapper tagMapper;
  private final EnvironmentHelperMethods environmentHelperMethods;

  public GetTag create(Long environmentId, PostTag body) {
    Environment environment = environmentHelperMethods.getEnvironmentOrThrow(environmentId);

    Tag tag = tagMapper.fromPost(body, environment);
    double score = calculateScore(tag);

    environmentHelperMethods.throwIfNotOwnerOrAdmin(environment);
    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);

    tagRepository.save(tag);
    return tagMapper.toGet(tag, score);
  }

  /**
   * Calculates score based on positive and negative flags.
   *
   * <p>Returns 0 if total flags are 6 or less, otherwise positive percentage
   */
  private double calculateScore(Tag tag) {
    int timesFlagged = tag.getNegativeFlaggedCount() + tag.getPositiveFlaggedCount();
    if (timesFlagged <= 6) {
      return 0;
    }

    double score = ((double) tag.getPositiveFlaggedCount() / timesFlagged) * 100;

    return Math.round(score * 100.0) / 100.0;
  }

  public void delete(Long environmentId, Long id) {
    Tag tag = environmentHelperMethods.getTagOrThrow(id);

    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);
    environmentHelperMethods.throwIfNotOwnerOrAdmin(tag.getEnvironment());

    tagRepository.deleteById(id);
  }

  public GetTag getById(Long environmentId, Long id) {
    Tag tag = environmentHelperMethods.getTagOrThrow(id);

    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);
    environmentHelperMethods.throwIfNotOwnerOrAdmin(tag.getEnvironment());

    double score = calculateScore(tag);
    return tagMapper.toGet(tag, score);
  }

  public List<GetTag> getAllTags(Long environmentId) {
    List<Tag> tags = tagRepository.findAllByEnvironmentId(environmentId);
    if (tags.isEmpty()) {
      return new ArrayList<GetTag>();
    }

    environmentHelperMethods.throwIfNotOwnerOrAdmin(tags.get(0).getEnvironment());

    return tags.stream().map(tag -> tagMapper.toGet(tag, calculateScore(tag))).toList();
  }

  public GetTag update(Long environmentId, Long id, PatchTag patch) {
    Tag tag = environmentHelperMethods.getTagOrThrow(id);
    Environment environment = tag.getEnvironment();

    environmentHelperMethods.throwIfNotOwnerOrAdmin(environment);
    environmentHelperMethods.throwIfNotInEnvironment(tag, environmentId);

    tagMapper.updateFields(tag, patch);
    double score = calculateScore(tag);

    tagRepository.save(tag);
    return tagMapper.toGet(tag, score);
  }
}
