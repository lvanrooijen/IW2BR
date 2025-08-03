package com.bella.IW2BR.domain.user.dto;

import java.util.UUID;

/**
 * User DTO containing user details and jwt-token
 *
 * <p>email address is used as the username
 *
 * @param id user ID
 * @param email email address of user
 * @param accessToken jwt access-token
 */
public record GetUserWithJwtToken(
    UUID id, String email, String username, String role, String accessToken) {}
