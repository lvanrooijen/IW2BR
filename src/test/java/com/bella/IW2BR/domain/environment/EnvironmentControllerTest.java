package com.bella.IW2BR.domain.environment;

import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bella.IW2BR.domain.environment.dto.GetEnvironment;
import com.bella.IW2BR.domain.environment.dto.PostEnvironment;
import com.bella.IW2BR.security.AuthenticationService;
import com.bella.IW2BR.security.jwt.JwtService;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
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
  private static PostEnvironment mockPostEnv;

  @Autowired private ObjectMapper objectMapper;

  @Autowired MockMvc mockMvc;
  @MockitoBean private EnvironmentService environmentService;
  @MockitoBean private AuthenticationService authenticationService;
  @MockitoBean private JwtService jwtService;

  @BeforeEach
  void setUp() {
    mockGetEnv =
        new GetEnvironment(VALID_ID, ENVIRONMENT_TITLE, ENVIRONMENT_DESCRIPTION, LocalDate.now());

    mockPostEnv = new PostEnvironment(ENVIRONMENT_TITLE, ENVIRONMENT_DESCRIPTION);
  }

  @Nested
  @DisplayName("Create Environment Endpoint Tests")
  class CreateEnvironmentEndpointTests {
    @Test
    void shouldCreateAndReturnEnvironment() throws Exception {
      when(environmentService.create(any())).thenReturn(mockGetEnv);

      mockMvc
          .perform(
              post(Endpoints.ENVIRONMENTS)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(mockPostEnv))
                  .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.title").value(ENVIRONMENT_TITLE))
          .andExpect(jsonPath("$.description").value(ENVIRONMENT_DESCRIPTION));
    }
  }
}
