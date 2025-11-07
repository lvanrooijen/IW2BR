package com.bella.IW2BR.domain.environment.dto;

import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import com.bella.IW2BR.domain.environment.Environment;
import org.junit.jupiter.api.Test;

class GetFullEnvironmentTest {
  @Test
  void shouldMapEnvironmentToDto() {
    Environment environment = getMockEnvironment();

    GetFullEnvironment dto = GetFullEnvironment.to(environment);

    assertNotNull(dto);
    assertTrue(dto.noteCollections().isEmpty());
    assertTrue(dto.flashcardDecks().isEmpty());
    assertTrue(dto.exams().isEmpty());
    assertTrue(dto.examAttempts().isEmpty());
    assertTrue(dto.tags().isEmpty());
  }
}
