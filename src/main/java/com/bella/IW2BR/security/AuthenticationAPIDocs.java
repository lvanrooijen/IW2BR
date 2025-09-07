package com.bella.IW2BR.security;

import static com.bella.IW2BR.utils.constants.ApiDocs.APIDocumentationConstants.*;

import com.bella.IW2BR.domain.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import javax.swing.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This interface contains all the annotations to provide Swagger documentation for the user
 * controller
 */
public interface AuthenticationAPIDocs {

  @Operation(summary = "Register user")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = CREATED),
    @ApiResponse(
        responseCode = "400",
        description =
            INVALID_REQUEST_BODY
                + SEPARATOR
                + "Email already in use"
                + SEPARATOR
                + "Invalid user role provided")
  })
  public ResponseEntity<GetUserWithJwtToken> register(PostUser body, HttpServletResponse response);

  @Operation(summary = "login User")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "404", description = USER_404),
    @ApiResponse(responseCode = "400", description = "Invalid password")
  })
  public ResponseEntity<GetUserWithJwtToken> login(LoginUser body, HttpServletResponse response);

  @Operation(
      summary = "refresh token",
      description =
          "creates a new refresh token for the user and stores it in HTTP only cookie. refresh token accessible via /api/v1/auth")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(
        responseCode = "401",
        description =
            "Invalid Refresh token"
                + SEPARATOR
                + "Missing refresh token"
                + SEPARATOR
                + "Refresh token is revoked"
                + SEPARATOR
                + "Refresh token is expired"
                + SEPARATOR
                + "Provided refresh token does not match users refresh token")
  })
  public ResponseEntity<GetUserWithJwtToken> refreshToken(
      HttpServletRequest request, HttpServletResponse response);

  @Operation(
      summary = "Patch User",
      description = "patch user information, password patch not included")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "404", description = USER_404),
    @ApiResponse(responseCode = "400", description = INVALID_REQUEST_BODY)
  })
  public ResponseEntity<GetUser> updateUser(UUID id, PatchUser patch);

  @Operation(summary = "Delete User")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = DELETED),
    @ApiResponse(responseCode = "404", description = USER_404)
  })
  public ResponseEntity<Void> deleteUser(UUID id);

  @Operation(
      summary = "Logout User",
      description = "revokes the refresh token and blacklists the access token")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = SUCCESS),
    @ApiResponse(responseCode = "401", description = "Invalid refresh token")
  })
  public ResponseEntity<Void> logoutUser(HttpServletRequest request);
}
