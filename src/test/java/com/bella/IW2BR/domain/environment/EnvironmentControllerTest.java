package com.bella.IW2BR.domain.environment;

import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.*;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bella.IW2BR.domain.environment.dto.GetEnvironment;
import com.bella.IW2BR.domain.environment.dto.GetFullEnvironment;
import com.bella.IW2BR.domain.environment.dto.PatchEnvironment;
import com.bella.IW2BR.domain.environment.dto.PostEnvironment;
import com.bella.IW2BR.security.AuthenticationService;
import com.bella.IW2BR.security.jwt.JwtService;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EnvironmentController.class)
@AutoConfigureMockMvc(addFilters = false)
class EnvironmentControllerTest {
  private static GetEnvironment mockGetEnv;
  private static GetFullEnvironment mockGetFullEnv;
  private static PostEnvironment mockPostEnv;
  private static PatchEnvironment mockPatchEnv;

  @Autowired private ObjectMapper objectMapper;

  @Autowired MockMvc mockMvc;
  @MockitoBean private EnvironmentService environmentService;
  @MockitoBean private AuthenticationService authenticationService;
  @MockitoBean private JwtService jwtService;

  @BeforeEach
  void setUp() {
    mockGetEnv =
        new GetEnvironment(VALID_ID, ENVIRONMENT_TITLE, ENVIRONMENT_DESCRIPTION, LocalDate.now());
    mockGetFullEnv =
        new GetFullEnvironment(
            VALID_ID,
            ENVIRONMENT_TITLE,
            ENVIRONMENT_DESCRIPTION,
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList());
    mockPostEnv = new PostEnvironment(ENVIRONMENT_TITLE, ENVIRONMENT_DESCRIPTION);
    mockPatchEnv = new PatchEnvironment("Patched Title", "Patched Description");
  }

  @Nested
  @DisplayName("Create Environment Endpoint Tests")
  class CreateEnvironmentEndpointTests {
    @Test
    @DisplayName("Create method without requesst body should return bad request")
    void createWithoutRequestBodyShouldReturnBadRequest() throws Exception {
      mockMvc
          .perform(post(Endpoints.ENVIRONMENTS).contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Create method should Create and return Environment")
    void shouldCreateAndReturnEnvironment() throws Exception {
      when(environmentService.create(mockPostEnv)).thenReturn(mockGetEnv);

      mockMvc
          .perform(
              post(Endpoints.ENVIRONMENTS)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(mockPostEnv))
                  .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.title").value(ENVIRONMENT_TITLE))
          .andExpect(jsonPath("$.description").value(ENVIRONMENT_DESCRIPTION))
          .andExpect(header().string("Location", endsWith("/" + mockGetEnv.id())));

      verify(environmentService).create(mockPostEnv);
    }

    @Test
    @DisplayName("Get should get an Environment")
    void shouldGetEnvironment() throws Exception {
      when(environmentService.getFullById(VALID_ID)).thenReturn(mockGetFullEnv);

      mockMvc
          .perform(
              get(Endpoints.ENVIRONMENTS + "/" + VALID_ID).contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.title").value(ENVIRONMENT_TITLE))
          .andExpect(jsonPath("$.description").value(ENVIRONMENT_DESCRIPTION));

      verify(environmentService).getFullById(VALID_ID);
    }

    @Test
    @DisplayName("GetAll Should Get a list of environments")
    void getAllShouldReturnAListOfGetEnvironments() throws Exception {
      ArrayList<GetEnvironment> environmentList = new ArrayList<>();
      environmentList.add(mockGetEnv);

      when(environmentService.getAll()).thenReturn(environmentList);

      mockMvc
          .perform(get(Endpoints.ENVIRONMENTS).contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].title").value(ENVIRONMENT_TITLE))
          .andExpect(jsonPath("$[0].description").value(ENVIRONMENT_DESCRIPTION));

      verify(environmentService).getAll();
    }

    @Test
    @DisplayName("Patch Should return patched Environment")
    void patchShouldUpdateAndReturnUpdatedEnvironment() throws Exception {
      GetEnvironment patchedEnv =
          new GetEnvironment(VALID_ID, "Patched Title", "Patched Environment", LocalDate.now());
      when(environmentService.update(VALID_ID, mockPatchEnv)).thenReturn(patchedEnv);

      mockMvc
          .perform(
              patch(Endpoints.ENVIRONMENTS + "/" + VALID_ID)
                  .content(objectMapper.writeValueAsString(mockPatchEnv))
                  .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());

      verify(environmentService).update(VALID_ID, mockPatchEnv);
    }

    @Test
    @DisplayName("Patch without a Request body should return Bad Request")
    void patchWithoutRequestBodyShouldReturnBadRequest() throws Exception {
      mockMvc
          .perform(
              patch(Endpoints.ENVIRONMENTS + "/" + VALID_ID)
                  .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Delete should return no content")
    void deleteShouldReturnNoContent() throws Exception {
      doNothing().when(environmentService).delete(VALID_ID);

      mockMvc
          .perform(delete(Endpoints.ENVIRONMENTS + "/" + VALID_ID))
          .andExpect(status().isNoContent());

      verify(environmentService).delete(VALID_ID);
    }
  }
}
