package com.bella.IW2BR.domain.user.dto;

import static com.bella.IW2BR.domain.user.dto.UserConstraints.*;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a User PATCH request
 *
 * @param email
 * @param firstName
 * @param lastName
 * @param role
 */
public record PatchUser(
    @Email(message = "Invalid email") String email,
    @Length(min = NAME_MIN, max = NAME_MAX, message = FIRST_NAME_INVALID_LENGTH) String firstName,
    @Length(min = NAME_MIN, max = NAME_MAX, message = LAST_NAME_INVALID_LENGTH_MSG) String lastName,
    String role) {}
