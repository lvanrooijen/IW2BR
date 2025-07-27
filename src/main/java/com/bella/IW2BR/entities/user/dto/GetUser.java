package com.bella.IW2BR.entities.user.dto;

import java.util.UUID;

/**
 * User DTO containing details returned in HTTP-requests
 *
 * <p>email address is used as the username
 *
 * @param id user ID
 * @param username email address of the user
 */
public record GetUser(UUID id, String username) {}
