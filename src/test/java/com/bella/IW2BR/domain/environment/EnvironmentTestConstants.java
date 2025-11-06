package com.bella.IW2BR.domain.environment;

import com.bella.IW2BR.domain.user.Role;
import com.bella.IW2BR.domain.user.User;
import java.time.LocalDate;

public class EnvironmentTestConstants {
  public static final Long VALID_ID = 1L;
  public static final Long INVALID_ID = -1L;
  public static final String ENVIRONMENT_TITLE = "Math";
  public static final String ENVIRONMENT_DESCRIPTION = "1+1=2";

  public static User getMockUser() {
    return User.builder()
        .firstName("user")
        .lastName("regular")
        .email("user@email.com")
        .role(Role.USER)
        .build();
  }

  public static User getMockAdmin() {
    return User.builder()
        .firstName("admin")
        .lastName("istrator")
        .email("admin@email.com")
        .role(Role.ADMIN)
        .build();
  }

  public static Environment getMockEnvironment() {
    return Environment.builder()
        .title(ENVIRONMENT_TITLE)
        .description(ENVIRONMENT_DESCRIPTION)
        .owner(getMockUser())
        .createdAt(LocalDate.now())
        .build();
  }
}
