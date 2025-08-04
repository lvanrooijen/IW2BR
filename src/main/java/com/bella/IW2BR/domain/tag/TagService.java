package com.bella.IW2BR.domain.tag;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentRepository;
import com.bella.IW2BR.domain.tag.dto.GetTag;
import com.bella.IW2BR.domain.tag.dto.PatchTag;
import com.bella.IW2BR.domain.tag.dto.PostTag;
import com.bella.IW2BR.domain.tag.dto.TagMapper;
import com.bella.IW2BR.domain.user.User;
import com.bella.IW2BR.exceptions.generic.IllegalActionException;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
import com.bella.IW2BR.security.AuthHelperService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
  private final TagRepository tagRepository;
  private final EnvironmentRepository environmentRepository;
  private final AuthHelperService authHelperService;
  private final TagMapper tagMapper;

  public GetTag createTag(PostTag body) {
    Environment environment =
        getEnvironmentOrThrow(body.environmentId(), "Failed to create tag, environment not found");

    User loggedInUser = authHelperService.getAuthenticatedUser();

    if (!environment.isOwner(loggedInUser.getId())) {
      throw new IllegalActionException(
          "Failed to create tag. creator is not the owner of the environment");
    }

    Tag tag = tagMapper.fromPostTag(body, environment);

    tagRepository.save(tag);

    double score = calculateScore(tag);

    return tagMapper.toGetTag(tag, score);
  }

  private Environment getEnvironmentOrThrow(Long environmentId, String exceptionMsgPrefix) {
    return environmentRepository
        .findById(environmentId)
        .orElseThrow(
            () ->
                new ItemNotFoundException(
                    exceptionMsgPrefix + ", could not find environment with id: " + environmentId));
  }

  private double calculateScore(Tag tag) {
    int timesFlagged = tag.getNegativeFlaggedCount() + tag.getPositiveFlaggedCount();

    if (timesFlagged <= 6) {
      return 0;
    }

    double score = ((double) tag.getPositiveFlaggedCount() / timesFlagged) * 100;

    return Math.round(score * 100.0) / 100.0;
  }

  public void deleteTag(Long id) {
    tagRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Could not delete, tag does not exist"));
    tagRepository.deleteById(id);
  }

  public GetTag getTagById(Long id) {
    Tag tag =
        tagRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Tag not found"));
    double score = calculateScore(tag);
    return tagMapper.toGetTag(tag, score);
  }

  public List<GetTag> getAllTags() {
    List<Tag> tags = tagRepository.findAll();
    return tags.stream().map(tag -> tagMapper.toGetTag(tag, calculateScore(tag))).toList();
  }

  public GetTag updateTag(Long id, PatchTag patch) {
    Tag tag =
        tagRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Tag not found"));
    tagMapper.updateTagFields(tag, patch);
    tagRepository.save(tag);
    double score = calculateScore(tag);
    return tagMapper.toGetTag(tag, score);
  }
}
