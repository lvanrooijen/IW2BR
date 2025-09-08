package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseForbiddenException;

/**
 * This exception should be thrown when a user without the proper permissions tries to access a
 * secured endpoint or resource.
 *
 * <p>This exception extends {@link BaseForbiddenException} and is handled by{@link
 * GlobalExceptionHandler#handleAccessDeniedException(Exception)}
 *
 * <p>Returns HTTP status code {@code 403 FORBIDDEN}
 *
 * <p>The exception message will be logged but NOT returned to the client
 */
public class AccessDeniedException extends BaseForbiddenException {
  public AccessDeniedException(String message) {
    super(message);
  }
}
