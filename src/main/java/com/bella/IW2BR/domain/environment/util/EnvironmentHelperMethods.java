package com.bella.IW2BR.domain.environment.util;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentRepository;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.exam.exam.ExamRepository;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeckRepository;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.Flashcard;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.FlashcardRepository;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollectionRepository;
import com.bella.IW2BR.domain.notecollection.note.Note;
import com.bella.IW2BR.domain.notecollection.note.NoteRepository;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.domain.tag.TagRepository;
import com.bella.IW2BR.domain.user.User;
import com.bella.IW2BR.exceptions.base.BaseBadRequestException;
import com.bella.IW2BR.exceptions.generic.IllegalActionException;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
import com.bella.IW2BR.exceptions.generic.ResourceNotInEnvironmentException;
import com.bella.IW2BR.security.AuthHelperService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** This class contains helper methods related to environments */
@Service
@RequiredArgsConstructor
public class EnvironmentHelperMethods {
  private final AuthHelperService authHelperService;
  private final EnvironmentRepository environmentRepository;
  private final TagRepository tagRepository;
  private final NoteCollectionRepository noteCollectionRepository;
  private final NoteRepository noteRepository;
  private final FlashcardDeckRepository flashcardDeckRepository;
  private final FlashcardRepository flashcardRepository;
  private final ExamRepository examRepository;

  /**
   * Verifies if the authenticated user is the owner of the environment, or has an admin role.
   *
   * @param environmentId
   * @throws IllegalActionException When the user is not the owner of the environment and is not
   *     admin role
   * @throws ItemNotFoundException when the environment is not found
   */
  public void throwIfNotOwnerOrAdmin(Long environmentId) {
    User user = authHelperService.getAuthenticatedUser();
    Environment environment =
        environmentRepository
            .findById(environmentId)
            .orElseThrow(() -> new ItemNotFoundException("environment not found"));
    if (!environment.isOwner(user.getId()) && !user.isAdmin()) {
      throw new IllegalActionException(
          "Failed to create tag. creator is not the owner of the environment");
    }
  }

  /**
   * Verifies if the ID's are the same, if not it will throw the passed exception
   *
   * @param expectedId ID as given in the URI
   * @param actualId ID that is connected to the entity
   * @param exception any exception that extends {@link BaseBadRequestException}
   * @throws BaseBadRequestException or co-variants if ID's do not match
   */
  public void throwIfIdMismatch(Long expectedId, Long actualId, BaseBadRequestException exception) {
    if (!Objects.equals(expectedId, actualId)) {
      throw exception;
    }
  }

  /**
   * Verifies if a class that implements EnvironmentMember is connected to the specified
   * environment.
   *
   * @param member
   * @param environmentId
   * @throws ResourceNotInEnvironmentException when the member isn't a member of the specified
   *     environment
   */
  public void throwIfNotInEnvironment(EnvironmentMember member, Long environmentId) {
    if (!Objects.equals(member.getEnvironment().getId(), environmentId)) {
      throw new ResourceNotInEnvironmentException(
          member.getClass().getSimpleName() + " is not a member of this environment");
    }
  }

  /**
   * Get the environment by ID
   *
   * @param environmentId ID of environment
   * @return {@link Environment}
   * @throws ItemNotFoundException when the environment is not found
   */
  public Environment getEnvironmentOrThrow(Long environmentId) {
    return environmentRepository
        .findById(environmentId)
        .orElseThrow(() -> new ItemNotFoundException("environment not found"));
  }

  /**
   * Gets a Tag by ID or throws {@link ItemNotFoundException}
   *
   * @param tagId ID of tag
   * @return {@link Tag}
   * @throws ItemNotFoundException when the tag can not be found
   */
  public Tag getTagOrThrow(Long tagId) {
    return tagRepository
        .findById(tagId)
        .orElseThrow(() -> new ItemNotFoundException("Tag not found"));
  }

  /**
   * Gets a note collection by ID
   *
   * @param noteCollectionId ID of note collection
   * @return {@link NoteCollection}
   * @throws ItemNotFoundException when the note collection can not be found
   */
  public NoteCollection getNoteCollectionOrThrow(Long noteCollectionId) {
    return noteCollectionRepository
        .findById(noteCollectionId)
        .orElseThrow(() -> new ItemNotFoundException("Note Collection not found"));
  }

  /**
   * Gets a Note by ID
   *
   * @param id ID of note
   * @return {@link Note}
   * @throws ItemNotFoundException when the note can not be found
   */
  public Note getNoteOrThrow(Long id) {
    return noteRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Note not found"));
  }

  /**
   * Gets a flashcard deck by ID
   *
   * @param id ID of the flashcard Deck
   * @return {@link FlashcardDeck}
   * @throws ItemNotFoundException when flashcard deck can not be found
   */
  public FlashcardDeck getFlashcardDeckOrThrow(Long id) {
    return flashcardDeckRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Flashcard deck not found"));
  }

  /**
   * Gets a flashcard by ID
   *
   * @param id
   * @return {@link Flashcard}
   * @throws ItemNotFoundException when flashcard can not be found
   */
  public Flashcard getFlashcardOrThrow(Long id) {
    return flashcardRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Flashcard not found"));
  }

  /**
   * Gets an Exam by ID
   *
   * @param id
   * @return {@link Exam}
   * @throws ItemNotFoundException
   */
  public Exam getExamOrThrow(Long id) {
    return examRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Exam not Found"));
  }
}
