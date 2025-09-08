package com.bella.IW2BR.exceptions.base;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;

/**
 * Base class for exceptions that need to return a not found http request.
 *
 * <p>Specific exceptions that need to be caught and return an HTTP status code 404 NOT FOUND can
 * extend this class, exceptions extending this class will automatically get caught and return a
 * problem detail with http status of not found, and the provided exception message as detail.
 *
 * <p>Exceptions will be caught and handled by the following method {@link
 * GlobalExceptionHandler#handleEntitiesNotFoundExceptions(Exception e)}
 */
public abstract class BaseNotFoundException extends RuntimeException {
  public BaseNotFoundException(String message) {
    super(message);
  }
}
