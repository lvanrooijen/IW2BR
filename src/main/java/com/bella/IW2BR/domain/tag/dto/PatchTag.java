package com.bella.IW2BR.domain.tag.dto;

import static com.bella.IW2BR.domain.tag.dto.TagConstraints.*;

import com.bella.IW2BR.domain.tag.Tag;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Tag PATCH request
 *
 * @param title
 * @param description
 * @param isFlaggedPositive
 * @param isFlaggedNegative
 */
public record PatchTag(
    @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG) String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description,
    Boolean isFlaggedPositive,
    Boolean isFlaggedNegative) {
  /**
   * Updates fields and returns Patched {@link Tag}
   *
   * @param tag {@link Tag}
   * @param patch {@link PatchTag}
   * @return Patched {@link Tag}
   */
  public static Tag patch(Tag tag, PatchTag patch) {
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
    return tag;
  }
}
