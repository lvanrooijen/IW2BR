package com.bella.IW2BR.domain.user.dto;

import com.bella.IW2BR.domain.user.User;
import org.springframework.stereotype.Component;

/**
 * Service that handles mapping between DTOs and the entity.
 *
 * <p>Methods:
 *
 * <ul>
 *   <li>Map {@link User} entity to {@link GetUser}
 *   <li>Patch {@link User} with new data from {@link PatchUser}
 * </ul>
 */
@Component
public class UserMapper {
  /**
   * Creates a new {@link GetUser} object from a {@link User} object
   *
   * @param user entity
   * @return {@link GetUser} dto
   */
  public GetUser toGet(User user) {
    return new GetUser(user.getId(), user.getEmail());
  }

  /**
   * @param user entity
   * @param accessToken jwt-token short duration access token
   * @return {@link GetUserWithJwtToken} dto
   */
  public GetUserWithJwtToken toGetUserWithJwtToken(User user, String accessToken) {
    return new GetUserWithJwtToken(
        user.getId(),
        user.getEmail(),
        user.getFullName(),
        user.getRole().substring(5),
        accessToken);
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
  public User updateFields(User user, PatchUser patch) {
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
