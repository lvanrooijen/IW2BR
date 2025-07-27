package com.bella.IW2BR.entities.user.dto;

/**
 * User DTO used for logging in a user
 *
 * @param username
 * @param password
 */
public record LoginUser(String username, String password) {}
