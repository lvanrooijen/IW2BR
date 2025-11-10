package com.bella.IW2BR.domain.environment.dto;

import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.getMockUser;
import static org.junit.jupiter.api.Assertions.*;

import com.bella.IW2BR.domain.environment.Environment;
import org.junit.jupiter.api.Test;

class PostEnvironmentTest {
  @Test
  void shouldMapToEnvironment() {
    PostEnvironment post = new PostEnvironment("hello", "world");

    Environment environment = PostEnvironment.from(post, getMockUser());

    assertEquals("hello", environment.getTitle());
    assertEquals("world", environment.getDescription());
  }
}
