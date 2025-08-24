package com.bella.IW2BR.domain.tag.dto;

import static com.bella.IW2BR.domain.tag.dto.TagConstraints.*;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Tag POST request
 *
 * @param title
 * @param description
 */
public record PostTag(
    @NotBlank @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG)
        String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {
  /**
   * Maps {@link PostTag} dto to {@link Tag}
   *
   * @param dto {@link PostTag}
   * @param environment {@link Environment}
   * @return {@link Tag}
   */
  public static Tag from(PostTag dto, Environment environment) {
    return Tag.builder()
        .title(dto.title())
        .description(dto.description)
        .environment(environment)
        .build();
  }
}
