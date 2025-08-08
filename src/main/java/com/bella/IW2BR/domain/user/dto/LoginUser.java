package com.bella.IW2BR.domain.user.dto;

/**
 * DTO representing the request body of a User POST (login user) request
 *
 * @param username
 * @param password
 */
public record LoginUser(String username, String password) {}
