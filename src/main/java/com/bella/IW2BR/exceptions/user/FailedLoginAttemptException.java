package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;

/**
 * This exception should be thrown when a user login is failed.
 *
 * <p>handled by {@link GlobalExceptionHandler#handleFailedLoginAttemptException(Exception)}
 */
public class FailedLoginAttemptException extends RuntimeException {
  public FailedLoginAttemptException(String message) {
    super(message);
  }
}
