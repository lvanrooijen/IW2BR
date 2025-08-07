package com.bella.IW2BR.domain.tag.dto;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.tag.Tag;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class TagMapper {
  public GetTag toGet(Tag tag, double score) {
    return new GetTag(
        tag.getId(), tag.getTitle(), tag.getDescription(), score, tag.getEnvironment().getId());
  }

  public Tag fromPost(PostTag body, Environment environment) {
    return Tag.builder()
        .title(body.title())
        .description(body.description())
        .lastSeen(LocalDate.now())
        .environment(environment)
        .build();
  }

  public void updateFields(Tag tag, PatchTag patch) {
    if (patch.title() != null) {
      tag.setTitle(patch.title());
    }
    if (patch.description() != null) {
      tag.setDescription(patch.description());
    }
    if (patch.isFlaggedNegative() != null && patch.isFlaggedNegative()) {
      tag.flagNegative();
    }
    if (patch.isFlaggedPositive() != null && patch.isFlaggedPositive()) {
      tag.flagPositive();
    }
  }
}
