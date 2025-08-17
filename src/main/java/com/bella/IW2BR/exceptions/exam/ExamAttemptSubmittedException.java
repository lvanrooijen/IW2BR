package com.bella.IW2BR.exceptions.exam;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when a client is trying to submit an exam attempt that has
 * already been submitted.
 *
 * <p>It extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 */
public class ExamAttemptSubmittedException extends BaseBadRequestException {
  public ExamAttemptSubmittedException(String message) {
    super(message);
  }
}
