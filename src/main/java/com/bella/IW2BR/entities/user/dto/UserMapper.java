package com.bella.IW2BR.entities.user.dto;

import com.bella.IW2BR.entities.user.User;
import org.springframework.stereotype.Component;

/** This class is used to convert user related DTO's to entities and vice versa */
@Component
public class UserMapper {
  /**
   * Creates a new {@link GetUser} object from a {@link User} object
   *
   * @param user entity
   * @return {@link GetUser} dto
   */
  public GetUser toGetUserDto(User user) {
    return new GetUser(user.getId(), user.getEmail());
  }

  /**
   * @param user entity
   * @param accessToken jwt-token short duration access token
   * @param refreshToken jwt-token used as refresh token
   * @return {@link GetUserWithJwtToken} dto
   */
  public GetUserWithJwtToken toGetUserWithJwtToken(
      User user, String accessToken, String refreshToken) {
    return new GetUserWithJwtToken(user.getId(), user.getEmail(), accessToken);
  }

  /**
   * Returns a {@link User} object with updated fields provided by {@link PatchUser} object
   *
   * <p>return {@link User} to enable method chaining
   *
   * @param user entity
   * @param patch {@link PatchUser}
   * @return {@link User} updated user object
   */
  public User updateUserFields(User user, PatchUser patch) {
    if (patch.firstName() != null) {
      user.setFirstName(patch.firstName());
    }
    if (patch.lastName() != null) {
      user.setLastName(patch.lastName());
    }
    if (patch.email() != null) {
      user.setEmail(patch.email());
    }
    return user;
  }
}
