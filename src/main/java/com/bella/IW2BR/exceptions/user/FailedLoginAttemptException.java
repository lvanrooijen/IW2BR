package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;

/**
 * This exception should be thrown when a user login is failed.
 *
 * <p>handled by {@link GlobalExceptionHandler#handleFailedLoginAttemptException(Exception)}
 *
 * <p>Return HTTP status code {@code 400 BAD REQUEST}
 *
 * <p>The exception message is logged but NOT returned to the client.
 *
 * <p>A default message {@code Invalid username and/or password} will be returned to the client
 */
public class FailedLoginAttemptException extends RuntimeException {
  public FailedLoginAttemptException(String message) {
    super(message);
  }
}
