package com.bella.IW2BR.domain.user.dto;

import java.util.UUID;

/**
 * DTO representing how the User is returned to the client
 *
 * <p>email address is used as the username
 *
 * @param id
 * @param email
 */
public record GetUser(UUID id, String username) {}
