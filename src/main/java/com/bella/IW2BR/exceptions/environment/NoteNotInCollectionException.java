package com.bella.IW2BR.exceptions.environment;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when the given note is not a member of the specified Note
 * collection
 *
 * <p>It extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 */
public class NoteNotInCollectionException extends BaseBadRequestException {
  public NoteNotInCollectionException(String message) {
    super(message);
  }
}
