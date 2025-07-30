package com.bella.IW2BR.security;

import com.bella.IW2BR.entities.user.Role;
import com.bella.IW2BR.entities.user.User;
import com.bella.IW2BR.entities.user.UserRepository;
import com.bella.IW2BR.entities.user.dto.*;
import com.bella.IW2BR.events.userregistration.UserRegistrationPublisher;
import com.bella.IW2BR.exceptions.user.FailedLoginException;
import com.bella.IW2BR.exceptions.user.InvalidUserRoleException;
import com.bella.IW2BR.exceptions.user.UserAlreadyRegisteredException;
import com.bella.IW2BR.exceptions.user.UserNotFoundException;
import com.bella.IW2BR.security.jwt.JwtService;
import com.bella.IW2BR.security.refreshtoken.RefreshToken;
import com.bella.IW2BR.security.refreshtoken.RefreshTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** Handles the business logic related to users */
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final UserMapper userMapper;
  private final UserRegistrationPublisher eventPublisher;
  private final RefreshTokenRepository refreshTokenRepository;

  /**
   * Creates a user
   *
   * @param body {@link PostUser} DTO
   * @return {@link GetUser}
   * @throws UserAlreadyRegisteredException if a user with this email address is present in the
   *     database
   * @throws InvalidUserRoleException if an invalid user role is passed
   */
  @Transactional
  public GetUserWithJwtToken registerUser(PostUser body, HttpServletResponse response) {
    if (userRepository.findByEmailIgnoreCase(body.email()).isPresent()) {
      throw new UserAlreadyRegisteredException(
          String.format("User with email %s already registered", body.email()));
    }

    Role role;
    try {
      role = Role.valueOf(body.role().toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new InvalidUserRoleException(String.format("Invalid role: %s", body.role()));
    }

    User user =
        User.builder()
            .email(body.email())
            .password(passwordEncoder.encode(body.password()))
            .firstName(body.firstName())
            .lastName(body.lastName())
            .role(role)
            .build();

    userRepository.save(user);

    eventPublisher.publishUserRegistrationEvent(user);

    String token = jwtService.generateTokenForUser(user);

    RefreshToken refreshToken =
        RefreshToken.builder()
            .token(jwtService.generateRefreshTokenForUser(user))
            .user(user)
            .isRevoked(false)
            .build();
    refreshTokenRepository.save(refreshToken);

    storeRefreshTokenInHttpServletResponse(response, refreshToken.getToken());

    return new GetUserWithJwtToken(user.getId(), user.getUsername(), token);
  }

  /**
   * handles user login
   *
   * @param requestBody containing login credentials of user
   * @return {@link GetUserWithJwtToken} dto
   */
  @Transactional
  public GetUserWithJwtToken login(LoginUser requestBody, HttpServletResponse response) {
    User user =
        userRepository
            .findByEmailIgnoreCase(requestBody.username())
            .orElseThrow(() -> new UserNotFoundException("invalid username and/or password"));

    if (!passwordEncoder.matches(requestBody.password(), user.getPassword())) {
      throw new FailedLoginException("invalid username and/or password");
    }

    String token = jwtService.generateTokenForUser(user);

    RefreshToken refreshToken =
        refreshTokenRepository
            .findByUser(user)
            .orElseThrow(() -> new FailedLoginException("This user does not have a refresh token"));

    refreshToken.setToken(jwtService.generateRefreshTokenForUser(user));
    refreshTokenRepository.save(refreshToken);
    storeRefreshTokenInHttpServletResponse(response, refreshToken.getToken());

    return userMapper.toGetUserWithJwtToken(user, token, refreshToken.getToken());
  }

  /**
   * Updates a user and saves it in the database
   *
   * @param id represents the user id
   * @param patch {@link PatchUser}
   * @return {@link GetUser}
   * @throws EntityNotFoundException when a user with this ID is not found
   */
  public GetUser updateUser(UUID id, PatchUser patch) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(
                () -> new UserNotFoundException(String.format("No user with id %s found", id)));

    userMapper.updateUserFields(user, patch);
    userRepository.save(user);
    return userMapper.toGetUserDto(user);
  }

  /**
   * Deletes a user
   *
   * @param id represents user id
   * @throws EntityNotFoundException when user ID is not present in the database
   */
  public void deleteUser(UUID id) {
    userRepository
        .findById(id)
        .orElseThrow(
            () ->
                new UserNotFoundException(
                    String.format("Can't delete user, user with id %s found", id)));
    userRepository.deleteById(id);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmailIgnoreCase(username).orElse(null);
  }

  public void storeRefreshTokenInHttpServletResponse(
      HttpServletResponse response, String refreshToken) {
    ResponseCookie cookie =
        ResponseCookie.from("refreshToken", refreshToken)
            .httpOnly(true)
            .secure(true)
            .path("/auth")
            .maxAge(Duration.ofDays(7))
            .sameSite("Strict")
            .build();

    response.addHeader("Set-Cookie", cookie.toString());
  }

  public GetUserWithJwtToken refreshToken(
      HttpServletRequest request, HttpServletResponse response) {
    //TODO FINISH ME!
    return null;

  }
}
