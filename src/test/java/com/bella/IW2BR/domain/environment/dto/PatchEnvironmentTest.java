package com.bella.IW2BR.domain.environment.dto;

import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.VALID_ID;
import static org.junit.jupiter.api.Assertions.*;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentTestConstants;
import org.junit.jupiter.api.Test;

class PatchEnvironmentTest {

  @Test
  void shouldUpdateEnvironment() {
    Environment environment = EnvironmentTestConstants.getMockEnvironment();
    environment.setId(VALID_ID);
    PatchEnvironment patch = new PatchEnvironment("Test", "123");

    PatchEnvironment.patch(environment, patch);

    assertEquals(VALID_ID, environment.getId());
    assertEquals("Test", environment.getTitle());
    assertEquals("123", environment.getDescription());
  }

  @Test
  void shouldUpdateEnvironmentTitle() {
    Environment environment = EnvironmentTestConstants.getMockEnvironment();
    environment.setId(VALID_ID);
    PatchEnvironment patch = new PatchEnvironment("Test", null);

    PatchEnvironment.patch(environment, patch);

    assertEquals(VALID_ID, environment.getId());
    assertEquals("Test", environment.getTitle());
    assertEquals("1+1=2", environment.getDescription());
  }

  @Test
  void shouldUpdateEnvironmentDescription() {
    Environment environment = EnvironmentTestConstants.getMockEnvironment();
    environment.setId(VALID_ID);
    PatchEnvironment patch = new PatchEnvironment(null, "123");

    PatchEnvironment.patch(environment, patch);

    assertEquals(VALID_ID, environment.getId());
    assertEquals("Math", environment.getTitle());
    assertEquals("123", environment.getDescription());
  }
}
