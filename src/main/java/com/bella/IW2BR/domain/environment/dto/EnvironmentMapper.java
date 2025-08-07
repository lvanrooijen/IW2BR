package com.bella.IW2BR.domain.environment.dto;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.user.User;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentMapper {
  public Environment fromPost(PostEnvironment dto, User user) {
    return Environment.builder()
        .title(dto.title())
        .description(dto.description())
        .owner(user)
        .createdAt(LocalDate.now())
        .build();
  }

  public GetEnvironment toGet(Environment environment) {
    return new GetEnvironment(
        environment.getId(),
        environment.getTitle(),
        environment.getDescription(),
        environment.getCreatedAt());
  }

  public Environment updateFields(Environment environment, PatchEnvironment patch) {
    if (patch.title() != null) {
      environment.setTitle(patch.title());
    }
    if (patch.description() != null) {
      environment.setDescription(patch.description());
    }
    return environment;
  }
}
