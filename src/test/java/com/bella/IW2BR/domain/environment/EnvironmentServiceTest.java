package com.bella.IW2BR.domain.environment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.bella.IW2BR.domain.environment.dto.GetEnvironment;
import com.bella.IW2BR.domain.environment.dto.PostEnvironment;
import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.user.Role;
import com.bella.IW2BR.domain.user.User;
import com.bella.IW2BR.exceptions.generic.IllegalActionException;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
import com.bella.IW2BR.security.AuthHelperService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("EnvironmentService Unit Tests")
class EnvironmentServiceTest {
  private static final String ENVIRONMENT_TITLE = "Math";
  private static final String ENVIRONMENT_DESCRIPTION = "1+1=2";
  private static final Long VALID_ID = 1L;
  private static final Long INVALID_ID = -1L;
  private static PostEnvironment postEnvironment;
  private static User mockUser;
  private static Environment mockEnvironment;

  @Mock private EnvironmentRepository environmentRepository;
  @Mock private AuthHelperService authHelperService;
  @Mock private EnvironmentHelperMethods environmentHelperMethods;

  @InjectMocks private EnvironmentService environmentService;

  @BeforeEach
  void setup() {
    postEnvironment = new PostEnvironment(ENVIRONMENT_TITLE, ENVIRONMENT_DESCRIPTION);
    mockUser = User.builder().email("email@email.com").role(Role.USER).build();
    mockEnvironment =
        Environment.builder()
            .title(ENVIRONMENT_TITLE)
            .description(ENVIRONMENT_DESCRIPTION)
            .owner(mockUser)
            .build();
  }

  @Nested
  @DisplayName("Create Environment Tests")
  class CreateEnvironmentTest {
    @Test
    void shouldCallGetAuthenticatedUser() {
      environmentService.create(postEnvironment);
      verify(authHelperService).getAuthenticatedUser();
    }

    @Test
    void shouldMapRequestBodyAndSaveIt() {
      when(authHelperService.getAuthenticatedUser()).thenReturn(mockUser);
      when(environmentRepository.save(any(Environment.class))).thenReturn(mockEnvironment);

      environmentService.create(postEnvironment);
      verify(environmentRepository).save(any(Environment.class));
    }

    @Test
    void shouldReturnGetEnvironment() {
      when(authHelperService.getAuthenticatedUser()).thenReturn(mockUser);
      when(environmentRepository.save(any(Environment.class))).thenReturn(mockEnvironment);

      GetEnvironment result = environmentService.create(postEnvironment);

      assertNotNull(result);
      assertEquals(result.title(), mockEnvironment.getTitle());
      assertEquals(result.description(), mockEnvironment.getDescription());
    }
  }

  @Nested
  @DisplayName("Get Environment By ID Tests")
  class GetEnvironmentByIdTests {
    @Test
    void shouldGetEnvironmentGivenValidID() {
      when(environmentRepository.findById(VALID_ID)).thenReturn(Optional.of(mockEnvironment));

      GetEnvironment result = environmentService.getById(VALID_ID);

      assertNotNull(result);
      assertEquals(result.title(), mockEnvironment.getTitle());
    }

    @Test
    void shouldThrowEnvironmentNotFoundExceptionGivenInvalidId() {
      when(environmentRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      assertThrows(ItemNotFoundException.class, () -> environmentService.getById(INVALID_ID));
    }

    @Test
    void shouldCallHelperMethodEnsureEnvironmentExistsAndUserIsOwnerOrAdmin() {
      when(environmentRepository.findById(VALID_ID)).thenReturn(Optional.of(mockEnvironment));

      environmentService.getById(VALID_ID);

      verify(environmentHelperMethods).ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID);
    }

    @Test
    void shouldThrowIllegalActionExceptionWhenNotOwnerOrAdmin() {
      when(environmentRepository.findById(VALID_ID)).thenReturn(Optional.of(mockEnvironment));

      doThrow(new IllegalActionException("mock exception"))
          .when(environmentHelperMethods)
          .ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID);

      assertThrows(IllegalActionException.class, () -> environmentService.getById(VALID_ID));
    }
  }
}
