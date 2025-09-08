package com.bella.IW2BR.exceptions.base;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;

/**
 * Base class for exceptions related to entities that need to return a forbidden status code.
 *
 * <p>Specific exceptions that need to be caught and return an HTTP status code 403 FORBIDDEN can
 * extend this class, exceptions extending this class will automatically get caught and return a
 * problem detail with http status of forbidden request, the provided exception message will be
 * logged but not included in the http response
 *
 * <p>Exceptions will be caught and handled by the following method {@link
 * GlobalExceptionHandler#handleForbiddenExceptions(Exception e)}
 */
public abstract class BaseForbiddenException extends RuntimeException {
  public BaseForbiddenException(String message) {
    super(message);
  }
}
