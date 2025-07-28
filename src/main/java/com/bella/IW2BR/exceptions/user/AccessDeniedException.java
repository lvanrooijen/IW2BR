package com.bella.IW2BR.exceptions.user;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseForbiddenException;

/**
 * This exception should be thrown when a user without the proper permissions tries to access a
 * secured endpoint or resource.
 *
 * <p>extends {@link BaseForbiddenException} and is handled by{@link
 * GlobalExceptionHandler#handleAccessDeniedException(Exception)}
 */
public class AccessDeniedException extends BaseForbiddenException {
  public AccessDeniedException(String message) {
    super(message);
  }
}
