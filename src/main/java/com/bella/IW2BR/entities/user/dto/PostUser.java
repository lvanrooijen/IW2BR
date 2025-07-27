package com.bella.IW2BR.entities.user.dto;

import com.bella.IW2BR.utils.constants.ExceptionMessages;
import com.bella.IW2BR.utils.customvalidators.password.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO used for creating new Users
 *
 * <p>All fields are required to contain a value
 *
 * @param email
 * @param password uses {@link Password} validator
 * @param firstName
 * @param lastName
 * @param role represents user role
 */
public record PostUser(
    @NotBlank @Email(message = "Invalid email address") String email,
    @NotBlank @Password String password,
    @NotBlank @Length(min = 3, max = 50, message = ExceptionMessages.FIRST_NAME_INVALID_LENGTH)
        String firstName,
    @NotBlank @Length(min = 3, max = 50, message = ExceptionMessages.LAST_NAME_INVALID_LENGTH)
        String lastName,
    @NotBlank String role) {}
