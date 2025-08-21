package com.bella.IW2BR.security;

import static com.bella.IW2BR.utils.constants.security.JwtConstants.REFRESH_TOKEN_EXPIRATION_DAYS;

import com.bella.IW2BR.domain.user.Role;
import com.bella.IW2BR.domain.user.User;
import com.bella.IW2BR.domain.user.UserRepository;
import com.bella.IW2BR.domain.user.dto.*;
import com.bella.IW2BR.events.userregistration.UserRegistrationPublisher;
import com.bella.IW2BR.exceptions.authentication.InvalidRefreshTokenException;
import com.bella.IW2BR.exceptions.user.FailedLoginAttemptException;
import com.bella.IW2BR.exceptions.user.InvalidUserRoleException;
import com.bella.IW2BR.exceptions.user.UserAlreadyRegisteredException;
import com.bella.IW2BR.exceptions.user.UserNotFoundException;
import com.bella.IW2BR.security.jwt.JwtService;
import com.bella.IW2BR.security.refreshtoken.RefreshToken;
import com.bella.IW2BR.security.refreshtoken.RefreshTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** Handles the business logic related to users */
@Slf4j
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

    String accessToken = jwtService.generateAccessTokenForUser(user);

    RefreshToken refreshToken =
        RefreshToken.builder()
            .token(jwtService.generateRefreshTokenForUser(user))
            .user(user)
            .isRevoked(false)
            .build();
    refreshTokenRepository.save(refreshToken);

    storeRefreshTokenInHttpServletResponse(response, refreshToken.getToken());

    return userMapper.toGetUserWithJwtToken(user, accessToken);
  }

  /**
   * handles user login
   *
   * @param body containing login credentials of user
   * @return {@link GetUserWithJwtToken} dto
   */
  @Transactional
  public GetUserWithJwtToken login(LoginUser body, HttpServletResponse response) {
    User user =
        userRepository
            .findByEmailIgnoreCase(body.email())
            .orElseThrow(() -> new UserNotFoundException("invalid username and/or password"));

    if (!passwordEncoder.matches(body.password(), user.getPassword())) {
      throw new FailedLoginAttemptException("invalid username and/or password");
    }

    String accessToken = jwtService.generateAccessTokenForUser(user);
    String newRefreshToken = jwtService.generateRefreshTokenForUser(user);

    RefreshToken refreshToken =
        refreshTokenRepository.findByUser(user).orElse(new RefreshToken(null, user, false));

    if (refreshToken.isRevoked()) {
      refreshToken.setRevoked(false);
    }
    refreshToken.setToken(newRefreshToken);
    refreshTokenRepository.save(refreshToken);

    storeRefreshTokenInHttpServletResponse(response, newRefreshToken);

    return userMapper.toGetUserWithJwtToken(user, accessToken);
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

    userMapper.updateFields(user, patch);
    userRepository.save(user);
    return userMapper.toGet(user);
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
            .path("/api/v1/auth")
            .maxAge(Duration.ofDays(7))
            .sameSite("Strict")
            .build();

    response.addHeader("Set-Cookie", cookie.toString());
  }

  public GetUserWithJwtToken refreshToken(
      HttpServletRequest request, HttpServletResponse response) {
    // haal de token uit de http only cookie
    String refreshTokenFromCookie = extractTokenFromCookie(request);
    log.warn("TOKEN FROM COOKIE: " + refreshTokenFromCookie);
    // haal de bijbehorende token uit de database
    RefreshToken refreshTokenFromDB =
        refreshTokenRepository
            .findByToken(refreshTokenFromCookie)
            .orElseThrow(() -> new InvalidRefreshTokenException("Refresh token not found"));
    // haal de user op die verbonden is aan de refreshtoken
    User user = refreshTokenFromDB.getUser();
    // voer validaties uit
    validateRefreshTokenOrThrow(refreshTokenFromCookie, refreshTokenFromDB);

    refreshTokenRepository.save(updateRefreshToken(refreshTokenFromDB));
    storeRefreshTokenInHttpServletResponse(response, refreshTokenFromDB.getToken());

    String accessToken = jwtService.generateAccessTokenForUser(user);
    return userMapper.toGetUserWithJwtToken(user, accessToken);
  }

  public String extractTokenFromCookie(HttpServletRequest request) {
    return Arrays.stream(request.getCookies())
        .filter(cookie -> "refreshToken".equals(cookie.getName()))
        .map(Cookie::getValue)
        .findFirst()
        .orElse(null);
  }

  public User getAuthenticatedUser() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  public void validateRefreshTokenOrThrow(
      String refreshTokenFromCookie, RefreshToken refreshTokenFromDB) {
    if (refreshTokenFromCookie == null) {
      throw new InvalidRefreshTokenException("http request does not contain a refresh token");
    }
    if (refreshTokenFromDB.isRevoked()) {
      throw new InvalidRefreshTokenException("refresh-token is revoked");
    }
    if (jwtService.isTokenExpired(refreshTokenFromDB.getToken())) {
      throw new InvalidRefreshTokenException("refresh-token is expired");
    }
    if (refreshTokenFromDB.getExpiresAt().isBefore(LocalDate.now())) {
      throw new InvalidRefreshTokenException("refresh-token is expired");
    }
    if (!refreshTokenFromCookie.equals(refreshTokenFromDB.getToken())) {
      throw new InvalidRefreshTokenException("refresh-token does not match refresh token of user");
    }

    // TODO komen de users overeen?
  }

  @Transactional
  public RefreshToken updateRefreshToken(RefreshToken refreshToken) {
    String newRefreshToken = jwtService.generateRefreshTokenForUser(refreshToken.getUser());
    refreshToken.setToken(newRefreshToken);
    refreshToken.setExpiresAt(LocalDate.now().plusDays(REFRESH_TOKEN_EXPIRATION_DAYS));
    return refreshToken;
  }

  public void logoutUser(HttpServletRequest request) {
    String refreshTokenFromCookie = extractTokenFromCookie(request);
    RefreshToken refreshTokenFromDB =
        refreshTokenRepository
            .findByToken(refreshTokenFromCookie)
            .orElseThrow(
                () ->
                    new InvalidRefreshTokenException(
                        String.format(
                            "[InvalidRefreshToken] refresh token does not exist. RefreshToken=%s",
                            refreshTokenFromCookie)));

    refreshTokenFromDB.setRevoked(true);
    refreshTokenRepository.delete(refreshTokenFromDB);
  }
}
