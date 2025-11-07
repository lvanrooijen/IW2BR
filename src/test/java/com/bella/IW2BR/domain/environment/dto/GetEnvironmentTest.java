package com.bella.IW2BR.domain.environment.dto;

import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.VALID_ID;
import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.getMockEnvironment;
import static org.junit.jupiter.api.Assertions.*;

import com.bella.IW2BR.domain.environment.Environment;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class GetEnvironmentTest {

  @Test
  void shouldMapEnvironmentToDto() {
    Environment environment = getMockEnvironment();
    environment.setId(VALID_ID);
    environment.setCreatedAt(LocalDate.now());

    GetEnvironment dto = GetEnvironment.to(environment);

    assertEquals(environment.getId(),dto.id());
    assertEquals(environment.getTitle(), dto.title());
    assertEquals(environment.getDescription(), dto.description());
    assertEquals(environment.getCreatedAt(),dto.createdAt());
  }
}
