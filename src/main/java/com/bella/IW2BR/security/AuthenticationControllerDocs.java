package com.bella.IW2BR.security;

import com.bella.IW2BR.domain.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

/**
 * This interface contains all the annotations to provide Swagger documentation for the user
 * controller
 */
public interface AuthenticationControllerDocs {

  @Operation(
      summary = "create user",
      description = "creates a new user and saves it in the database")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "user created and saved in the database"),
    @ApiResponse(
        responseCode = "400",
        description = "invalid request body, user already registered")
  })
  public ResponseEntity<GetUserWithJwtToken> registerUser(
      PostUser body, HttpServletResponse response);

  @Operation(summary = "update user", description = "updates user and save it in the database")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "user updated"),
    @ApiResponse(responseCode = "400", description = "invalid request body"),
    @ApiResponse(responseCode = "404", description = "user with given ID not found")
  })
  public ResponseEntity<GetUser> updateUser(UUID id, PatchUser patch);

  @Operation(summary = "delete user", description = "deletes a user from the database")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "user deleted"),
    @ApiResponse(responseCode = "404", description = "user with given ID not found")
  })
  public ResponseEntity<Void> deleteUser(UUID id);

  @Operation(summary = "login user", description = "login a user with a jwt token")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "user logged in")})
  public ResponseEntity<GetUserWithJwtToken> login(
      LoginUser requestBody, HttpServletResponse response);

  @Operation(summary = "refresh token", description = "retrieves a new access token if a valid refresh token is provided")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "accessToken refreshed"),
          @ApiResponse(responseCode = "401", description = "invalid refresh token, refresh token is revoked, refresh token is expired")
  })
  public ResponseEntity<GetUserWithJwtToken> refreshToken(
          HttpServletRequest request, HttpServletResponse response);
}
