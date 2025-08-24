package com.bella.IW2BR.domain.environment.dto;

import static com.bella.IW2BR.domain.tag.dto.TagConstraints.*;

import com.bella.IW2BR.domain.environment.Environment;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of an Environment PATCH request
 *
 * @param title
 * @param description
 */
public record PatchEnvironment(
    @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG) String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {
  /**
   * Updates fields of {@link Environment}
   *
   * @param environment {@link Environment}
   * @param patch {@link PatchEnvironment}
   * @return updated {@link Environment}
   */
  public static Environment patch(Environment environment, PatchEnvironment patch) {
    if (patch.title() != null) {
      environment.setTitle(patch.title());
    }
    if (patch.description() != null) {
      environment.setDescription(patch.description());
    }
    return environment;
  }
}
