package com.bella.IW2BR.exceptions.exam;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when a client is trying to submit an exam attempt that has
 * already been submitted.
 *
 * <p>This exception extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 *
 * <p>Returns HTTP status code {@code 400 BAD REQUEST}
 *
 * <p>The exception message is logged and returned to the client
 */
public class ExamAttemptSubmittedException extends BaseBadRequestException {
  public ExamAttemptSubmittedException(String message) {
    super(message);
  }
}
