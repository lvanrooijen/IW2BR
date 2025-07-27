package com.bella.IW2BR.entities.user.dto;

/**
 * User DTO containing details returned in HTTP-requests
 *
 * <p>email address is used as the username
 *
 * @param id user ID
 * @param username email address of user
 * @param token jwt-token
 */
public record GetUserWithJwtToken(Long id, String username, String token) {}
