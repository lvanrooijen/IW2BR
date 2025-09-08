package com.bella.IW2BR.exceptions.authentication;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;

/**
 * This exception should be thrown when an HTTP request does not contain a (valid) refresh-token
 *
 * <p>This exception is handled by {@link
 * GlobalExceptionHandler#handleInvalidRefreshTokenException(Exception)}
 *
 * <p>Returns HTTP status code {@code 401 UNAUTHORIZED}
 *
 * <p>The exception message is logged but not included in the HTTP response
 */
public class InvalidRefreshTokenException extends RuntimeException {
  public InvalidRefreshTokenException(String message) {
    super(message);
  }
}
