package com.bella.IW2BR.domain.environment.util;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentRepository;
import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.answer.AnswerRepository;
import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;
import com.bella.IW2BR.domain.exam.attempt.ExamAttemptRepository;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.exam.exam.ExamRepository;
import com.bella.IW2BR.domain.exam.question.Question;
import com.bella.IW2BR.domain.exam.question.QuestionRepository;
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
import com.bella.IW2BR.exceptions.exam.FinalisedExamException;
import com.bella.IW2BR.exceptions.exam.MissingArgumentException;
import com.bella.IW2BR.exceptions.exam.MissingEnvironmentException;
import com.bella.IW2BR.exceptions.generic.IllegalActionException;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
import com.bella.IW2BR.exceptions.generic.ResourceNotConnectedToParentException;
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
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;
  private final ExamAttemptRepository examAttemptRepository;

  /**
   * Verifies if the authenticated user is the owner of the environment, or has an admin role.
   *
   * <p>Checks by retrieving the authenticated user from the SecurityContext
   *
   * @param environmentId
   * @throws IllegalActionException When the user is not the owner of the environment and is not
   *     admin role
   * @throws ItemNotFoundException when the environment is not found
   */
  public void ensureEnvironmentExistsAndUserIsOwnerOrAdmin(Long environmentId) {
    User user = authHelperService.getAuthenticatedUser();
    Environment environment =
        environmentRepository
            .findById(environmentId)
            .orElseThrow(() -> new ItemNotFoundException("Environment not found"));
    if (!environment.isOwner(user.getId()) && !user.isAdmin()) {
      throw new IllegalActionException(
          "Only the owner or an administrator can perform this action.");
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
    String MissingArgumentMessage =
        "[Class=EnvironmentHelperMethods Method=throwIfIdMismatch] reason=";
    if (expectedId == null) {
      throw new MissingArgumentException(MissingArgumentMessage + "expectedId is NULL");
    }
    if (actualId == null) {
      throw new MissingArgumentException(MissingArgumentMessage + "actualId is NULL");
    }
    if (exception == null) {
      throw new MissingArgumentException(
          MissingArgumentMessage
              + "exception is NULL, you must provide an exception to be thrown in case of id mismatch");
    }
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
   * @throws ResourceNotConnectedToParentException when the member isn't a member of the specified
   *     environment
   */
  public void throwIfNotInEnvironment(EnvironmentMember member, Long environmentId) {
    if (member.getEnvironment() == null) {
      throw new MissingEnvironmentException(
          String.format(
              "[%s] Member does not have environment connected to it",
              member.getClass().getSimpleName()));
    }

    if (!Objects.equals(member.getEnvironment().getId(), environmentId)) {
      throw new ResourceNotConnectedToParentException(
          String.format(
              "%s is not a member of this environment", member.getClass().getSimpleName()));
    }
  }

  /**
   * Throws an exception if the exception is finalised.
   *
   * @param exam
   * @throws FinalisedExamException
   */
  public void throwIfExamIsFinalised(Exam exam) {
    if (exam == null) {
      throw new MissingArgumentException("Can not verify is exam is finalised, exam is NULL");
    }
    if (exam.isFinalised()) {
      throw new FinalisedExamException(
          "Altering an Exam or or it's related Questions and answers is not allowed after the Exam is finalised");
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
    if (environmentId == null) {
      throw new MissingArgumentException("Can not retrieve environment, ID is NULL");
    }
    return environmentRepository
        .findById(environmentId)
        .orElseThrow(() -> new ItemNotFoundException("Environment not found"));
  }

  /**
   * Gets a Tag by ID or throws {@link ItemNotFoundException}
   *
   * @param tagId ID of tag
   * @return {@link Tag}
   * @throws ItemNotFoundException when the tag can not be found
   */
  public Tag getTagOrThrow(Long tagId) {
    if (tagId == null) {
      throw new MissingArgumentException("Can not retrieve tag, ID is NULL");
    }
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
    if (noteCollectionId == null) {
      throw new MissingArgumentException("Can not retrieve NoteCollection, ID is NULL");
    }
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
    if (id == null) {
      throw new MissingArgumentException("Can not retrieve note, ID is NULL");
    }
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
    if (id == null) {
      throw new MissingArgumentException("Can not retrieve FlashcardDeck, ID is NULL");
    }
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
    if (id == null) {
      throw new MissingArgumentException("Could not retrieve Flashcard, ID is NULL");
    }
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
    if (id == null) {
      throw new MissingArgumentException("Could not retrieve Exam, ID is NULL");
    }
    return examRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Exam not found"));
  }

  /**
   * Gets an ExamAttempt by ID
   *
   * @param id
   * @return {@link ExamAttempt}
   * @throws ItemNotFoundException
   */
  public ExamAttempt getExamAttemptOrThrow(Long id) {
    if (id == null) {
      throw new MissingArgumentException("Could not retrieve Exam-attempt, ID is NULL");
    }
    return examAttemptRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Exam-attempt not Found"));
  }

  /**
   * Gets a Question by ID
   *
   * @param id
   * @return {@link Question}
   */
  public Question getQuestionOrThrow(Long id) {
    if (id == null) {
      throw new MissingArgumentException("Could not retrieve Question, ID is NULL");
    }
    return questionRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Question not found"));
  }

  public Answer getAnswerOrThrow(Long id) {
    if (id == null) {
      throw new MissingArgumentException("Could not retrieve Answer, ID is NULL");
    }
    return answerRepository
        .findById(id)
        .orElseThrow(() -> new ItemNotFoundException("Answer not found"));
  }
}
