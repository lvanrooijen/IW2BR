package com.bella.IW2BR.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bella.IW2BR.domain.user.Role;
import com.bella.IW2BR.domain.user.User;
import com.bella.IW2BR.exceptions.generic.IllegalActionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthHelperService Unit Tests")
class AuthHelperServiceTest {
  private User mockUser;
  @Mock private SecurityContext mockSecurityContext;
  @Mock private Authentication mockAuthentication;

  @InjectMocks private AuthHelperService authHelperService;

  @BeforeEach
  void setUp() {
    mockUser =
        User.builder()
            .firstName("Jane")
            .lastName("Doe")
            .email("jane_doe@email.com")
            .password("SecurePassword123!")
            .role(Role.USER)
            .build();
  }

  void setupSecurityContext() {
    when(mockAuthentication.getPrincipal()).thenReturn(mockUser);
    when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);
    SecurityContextHolder.setContext(mockSecurityContext);
  }

  @Nested
  @DisplayName("getAuthenticatedUser Tests")
  class GetAuthenticatedUser {
    @Test
    void shouldReturnAuthenticatedUser() {
      setupSecurityContext();

      User result = authHelperService.getAuthenticatedUser();

      assertNotNull(result);
      assertEquals(mockUser.getEmail(), result.getUsername());

      verify(mockSecurityContext).getAuthentication();
    }

    @Test
    void shouldThrowIllegalActionExceptionIfUserNotPresent() {
      setupSecurityContext();
      when(mockAuthentication.getPrincipal()).thenReturn(null);

      assertThrows(
          IllegalActionException.class,
          () -> {
            authHelperService.getAuthenticatedUser();
          });
    }
  }
}
