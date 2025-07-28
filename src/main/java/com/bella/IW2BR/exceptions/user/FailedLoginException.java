package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;

/**
 * This exception should be thrown when a user login is failed.
 *
 * <p>handled by {@link GlobalExceptionHandler#handleFailedLoginException(Exception)}
 */
public class FailedLoginException extends RuntimeException {
  public FailedLoginException(String message) {
    super(message);
  }
}
