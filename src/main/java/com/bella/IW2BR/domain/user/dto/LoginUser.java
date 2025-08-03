package com.bella.IW2BR.domain.user.dto;

/**
 * User DTO used for logging in a user
 *
 * @param username
 * @param password
 */
public record LoginUser(String username, String password) {}
