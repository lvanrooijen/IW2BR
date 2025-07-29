package com.bella.IW2BR.entities.environment.dto;

import com.bella.IW2BR.entities.environment.Environment;
import com.bella.IW2BR.entities.user.User;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentMapper {
  public Environment fromPostEnvironment(PostEnvironment dto, User user) {
    return Environment.builder()
        .title(dto.title())
        .description(dto.description())
        .owner(user)
        .createdAt(LocalDate.now())
        .build();
  }

  public GetEnvironment toGetEnvironment(Environment environment) {
    return new GetEnvironment(
        environment.getId(),
        environment.getTitle(),
        environment.getDescription(),
        environment.getCreatedAt());
  }

  public Environment updateEnvironmentFields(Environment environment, PatchEnvironment patch) {
    if (patch.title() != null) {
      environment.setTitle(patch.title());
    }
    if (patch.description() != null) {
      environment.setDescription(patch.description());
    }
    return environment;
  }
}
