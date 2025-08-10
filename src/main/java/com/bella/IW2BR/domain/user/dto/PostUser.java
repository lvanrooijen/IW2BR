package com.bella.IW2BR.domain.user.dto;

import static com.bella.IW2BR.domain.user.dto.UserConstraints.*;

import com.bella.IW2BR.utils.customvalidators.password.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a User POST (register user) request,
 *
 * @param email
 * @param password uses {@link Password} validator
 * @param firstName
 * @param lastName
 * @param role represents user role
 */
public record PostUser(
    @NotBlank @Email(message = INVALID_EMAIL_MSG) String email,
    @NotBlank @Password String password,
    @NotBlank @Length(min = NAME_MIN, max = NAME_MAX, message = FIRST_NAME_INVALID_LENGTH)
        String firstName,
    @NotBlank @Length(min = NAME_MIN, max = NAME_MAX, message = LAST_NAME_INVALID_LENGTH_MSG)
        String lastName,
    @NotBlank String role) {}
