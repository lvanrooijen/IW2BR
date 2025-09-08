package com.bella.IW2BR.exceptions.generic;

import com.bella.IW2BR.exceptions.GlobalExceptionHandler;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;

/**
 * Because this app uses a path variable structure to determine members, this exception should be
 * thrown when a member ID is not connected to an owning entities ID.
 *
 * <p>So for example if a Flashcard ID is not a member of the provided Flashcard Deck this exception
 * should be thrown
 *
 * <p>{@code environments/{environmentId}/flashcard_decks/{flashcardDeckId}/flashcards}
 *
 * <p>This exception extends {@link BaseBadRequestException} and is handled by {@link
 * GlobalExceptionHandler#handleBadRequestExceptions(Exception)}
 *
 * <p>Returns HTTPS status code {@code 400 BAD REQUEST}
 *
 * <p>The exception message is logged and returned to the client
 */
public class ResourceNotConnectedToParentException extends BaseBadRequestException {
  public ResourceNotConnectedToParentException(String message) {
    super(message);
  }
}
