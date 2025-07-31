package com.bella.IW2BR.exceptions.authentication;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseForbiddenException;

/**
 * This exception should be thrown when a http servlet request does not contain a refresh-token
 * extends {@link BaseForbiddenException} and is handled by {@link
 * GlobalExceptionHandler#handleForbiddenExceptions(Exception)} The exception message is not
 * returned but will be logged
 */
public class MissingRefreshTokenException extends BaseForbiddenException {
  public MissingRefreshTokenException(String message) {
    super(message);
  }
}
