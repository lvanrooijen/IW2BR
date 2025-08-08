package com.bella.IW2BR.exceptions.environment;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * This exception should be thrown when the given flashcard deck ID does not match the ID of the
 * path variable that represents the flashcardDeckId
 *
 * <p>It extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 */
public class FlashcardDeckMismatchException extends BaseBadRequestException {
  public FlashcardDeckMismatchException(String message) {
    super(message);
  }
}
