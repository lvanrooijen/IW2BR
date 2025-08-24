package com.bella.IW2BR.domain.environment.dto;

import com.bella.IW2BR.domain.environment.Environment;
import java.time.LocalDate;

/**
 * DTO representing how the Environment is returned to the client
 *
 * @param id
 * @param title
 * @param description
 * @param createdAt Date the Environment was created
 */
public record GetEnvironment(Long id, String title, String description, LocalDate createdAt) {

  /**
   * Maps {@link Environment} to {@link GetEnvironment}
   *
   * @param environment {@link Environment}
   * @return {@link GetEnvironment}
   */
  public static GetEnvironment to(Environment environment) {
    return new GetEnvironment(
        environment.getId(),
        environment.getTitle(),
        environment.getDescription(),
        environment.getCreatedAt());
  }
}
