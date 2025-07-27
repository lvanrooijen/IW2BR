package com.bella.IW2BR.entities.user;

/**
 * Represent User Roles
 *
 * <p>toString() returns roles with a "ROLE_ prefix"
 */
public enum Role {
  USER("ROLE_USER"),
  ADMIN("ROLE_ADMIN");

  private final String role;

  Role(String role) {
    this.role = role;
  }

  /**
   * Returns a String representation of this role
   *
   * <p>{@link Role} is prefixed with "ROLE_"
   *
   * @return {@link Role}
   */
  @Override
  public String toString() {
    return role;
  }
}
