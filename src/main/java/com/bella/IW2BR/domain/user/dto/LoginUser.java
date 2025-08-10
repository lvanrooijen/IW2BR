package com.bella.IW2BR.domain.user.dto;

import static com.bella.IW2BR.domain.user.dto.UserConstraints.INVALID_EMAIL_MSG;

import com.bella.IW2BR.utils.customvalidators.password.Password;
import jakarta.validation.constraints.Email;

/**
 * DTO representing the request body of a User POST (login user) request
 *
 * @param email
 * @param password
 */
public record LoginUser(
    @Email(message = INVALID_EMAIL_MSG) String email, @Password String password) {}
