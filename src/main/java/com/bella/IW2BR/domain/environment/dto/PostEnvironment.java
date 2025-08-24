package com.bella.IW2BR.domain.environment.dto;

import static com.bella.IW2BR.domain.environment.dto.EnvironmentConstraintsGlobal.*;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of an Environment POST request
 *
 * @param title
 * @param description
 */
public record PostEnvironment(
    @NotBlank @Length(min = TITLE_MIN, max = TITLE_MAX, message = INVALID_TITLE_LENGTH_MSG)
        String title,
    @Length(min = DESCRIPTION_MIN, max = DESCRIPTION_MAX, message = INVALID_DESCRIPTION_LENGTH_MSG)
        String description) {
  /**
   * Maps {@link PostEnvironment} to {@link Environment}
   *
   * @param dto {@link PostEnvironment}
   * @param user {@link User}
   * @return {@link Environment}
   */
  public static Environment from(PostEnvironment dto, User user) {
    return Environment.builder()
        .title(dto.title())
        .description(dto.description())
        .owner(user)
        .createdAt(LocalDate.now())
        .build();
  }
}
