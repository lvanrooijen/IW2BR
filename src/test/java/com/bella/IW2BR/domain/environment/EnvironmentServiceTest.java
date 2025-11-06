package com.bella.IW2BR.domain.environment;

import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.bella.IW2BR.domain.environment.dto.GetEnvironment;
import com.bella.IW2BR.domain.environment.dto.GetFullEnvironment;
import com.bella.IW2BR.domain.environment.dto.PatchEnvironment;
import com.bella.IW2BR.domain.environment.dto.PostEnvironment;
import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
import com.bella.IW2BR.security.AuthHelperService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
  private static PostEnvironment mockPostEnv;
  private static PatchEnvironment mockPatchEnv;

  @Mock private EnvironmentRepository environmentRepository;
  @Mock private AuthHelperService authHelperService;
  @Mock private EnvironmentHelperMethods environmentHelperMethods;

  @InjectMocks private EnvironmentService environmentService;

  @BeforeEach
  void setup() {
    mockPostEnv = new PostEnvironment(ENVIRONMENT_TITLE, ENVIRONMENT_DESCRIPTION);
    mockPatchEnv = new PatchEnvironment("Patched Title", "Patched Description");
  }

  @Nested
  @DisplayName("Create Environment Tests")
  class CreateEnvironmentTest {
    @Test
    void shouldMapRequestBodyAndSaveIt() {
      when(authHelperService.getAuthenticatedUser()).thenReturn(getMockUser());
      when(environmentRepository.save(any(Environment.class))).thenReturn(getMockEnvironment());

      environmentService.create(mockPostEnv);
      verify(environmentRepository).save(any(Environment.class));
    }

    @Test
    void shouldReturnGetEnvironment() {
      when(authHelperService.getAuthenticatedUser()).thenReturn(getMockUser());
      when(environmentRepository.save(any(Environment.class))).thenReturn(getMockEnvironment());

      GetEnvironment result = environmentService.create(mockPostEnv);

      assertNotNull(result);
      assertEquals(ENVIRONMENT_TITLE, result.title());
      assertEquals(ENVIRONMENT_DESCRIPTION, result.description());
    }
  }

  @Nested
  @DisplayName("Get Environment By ID Tests")
  class GetEnvironmentByIdTests {
    @Test
    void shouldGetEnvironmentGivenValidID() {
      when(environmentHelperMethods.getEnvironmentOrThrow(VALID_ID))
          .thenReturn(getMockEnvironment());

      GetEnvironment result = environmentService.getById(VALID_ID);

      assertNotNull(result);
      assertEquals(getMockEnvironment().getTitle(), result.title());
    }

    @Test
    void shouldCallHelperMethodEnsureEnvironmentExistsAndUserIsOwnerOrAdmin() {
      when(environmentHelperMethods.getEnvironmentOrThrow(VALID_ID))
          .thenReturn(getMockEnvironment());

      environmentService.getById(VALID_ID);

      verify(environmentHelperMethods).ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID);
    }
  }

  @Nested
  @DisplayName("Get Full environment By ID Tests")
  class GetFullEnvironmentByIdTests {
    @Test
    void shouldGetEnvironmentGivenAValidID() {
      Environment mockEnv = mock(Environment.class);

      when(environmentHelperMethods.getEnvironmentOrThrow(VALID_ID)).thenReturn(mockEnv);
      when(mockEnv.getNoteCollections()).thenReturn(Collections.emptyList());
      when(mockEnv.getExams()).thenReturn(Collections.emptyList());
      when(mockEnv.getExamAttempts()).thenReturn(Collections.emptyList());
      when(mockEnv.getTags()).thenReturn(Collections.emptyList());
      when(mockEnv.getFlashcardDecks()).thenReturn(Collections.emptyList());

      when(mockEnv.getNoteCollections()).thenReturn(Collections.emptyList());

      GetFullEnvironment result = environmentService.getFullById(VALID_ID);

      assertNotNull(result);
    }

    @Test
    void shouldCallHelperMethodEnsureEnvironmentExistsAndUserIsOwnerOrAdmin() {
      when(environmentHelperMethods.getEnvironmentOrThrow(VALID_ID))
          .thenReturn(getMockEnvironment());

      environmentService.getFullById(VALID_ID);

      verify(environmentHelperMethods).ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID);
    }
  }

  @Nested
  @DisplayName("Get all environments Tests")
  class GetAllEnvironmentTests {
    @Test
    void whenUserRoleShouldCallFindAllByOwnerMethod() {
      when(authHelperService.getAuthenticatedUser()).thenReturn(getMockUser());

      environmentService.getAll();

      verify(environmentRepository).findAllByOwner(any());
    }

    @Test
    void whenAdminRoleShouldCallFindAllMethod() {
      when(authHelperService.getAuthenticatedUser()).thenReturn(getMockAdmin());

      environmentService.getAll();

      verify(environmentRepository).findAll();
    }

    @Test
    void getAllShouldReturnGetEnvironmentList() {
      ArrayList<Environment> environmentList = new ArrayList<>();
      environmentList.add(getMockEnvironment());

      System.out.println(getMockEnvironment());

      when(authHelperService.getAuthenticatedUser()).thenReturn(getMockAdmin());
      when(environmentRepository.findAll()).thenReturn(environmentList);

      List<GetEnvironment> result = environmentService.getAll();

      assertNotNull(result);
      assertEquals(1, result.size());
      assertEquals(ENVIRONMENT_TITLE, result.get(0).title());
      assertEquals(ENVIRONMENT_DESCRIPTION, result.get(0).description());
    }
  }

  @Nested
  @DisplayName("Update Environment Tests")
  class UpdateEnvironmentTests {
    @Test
    void shouldSavePatchedMethod() {
      when(environmentHelperMethods.getEnvironmentOrThrow(VALID_ID))
          .thenReturn(getMockEnvironment());

      environmentService.update(VALID_ID, mockPatchEnv);

      verify(environmentRepository).save(any());
    }

    @Test
    void shouldReturnPatchedEnvironment() {
      when(environmentHelperMethods.getEnvironmentOrThrow(VALID_ID))
          .thenReturn(getMockEnvironment());

      GetEnvironment result = environmentService.update(VALID_ID, mockPatchEnv);

      assertNotNull(result);
      assertEquals("Patched Title", result.title());
      assertEquals("Patched Description", result.description());
    }
  }

  @Nested
  @DisplayName("Delete environment tests")
  class DeleteEnvironmentTests {
    @Test
    void shouldCallEnsureEnvironmentExistsAndUserIsOwnerOrAdminMethod() {
      environmentService.delete(VALID_ID);

      verify(environmentHelperMethods).ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID);
    }

    @Test
    void shouldCalDeleteByIdMethod() {
      environmentService.delete(VALID_ID);

      verify(environmentRepository).deleteById(VALID_ID);
    }

    @Test
    void shouldNotDeleteIfEnsureEnvironmentExistsAndUserIsOwnerOrAdminFails() {
      doThrow(new ItemNotFoundException(""))
          .when(environmentHelperMethods)
          .ensureEnvironmentExistsAndUserIsOwnerOrAdmin(INVALID_ID);

      assertThrows(ItemNotFoundException.class, () -> environmentService.delete(INVALID_ID));

      verify(environmentRepository, never()).deleteById(INVALID_ID);
    }
  }
}
