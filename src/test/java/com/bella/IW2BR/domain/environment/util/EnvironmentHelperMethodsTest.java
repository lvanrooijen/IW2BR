package com.bella.IW2BR.domain.environment.util;

import static com.bella.IW2BR.domain.environment.EnvironmentTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.bella.IW2BR.exceptions.exam.ExamAttemptSubmittedException;
import com.bella.IW2BR.exceptions.exam.FinalisedExamException;
import com.bella.IW2BR.exceptions.exam.MissingArgumentException;
import com.bella.IW2BR.exceptions.exam.MissingEnvironmentException;
import com.bella.IW2BR.exceptions.generic.IllegalActionException;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
import com.bella.IW2BR.exceptions.generic.ResourceNotConnectedToParentException;
import com.bella.IW2BR.security.AuthHelperService;
import java.util.Optional;
import javax.swing.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Tests for the EnvironmentHelperMethods Class")
class EnvironmentHelperMethodsTest {
  @Mock private EnvironmentRepository environmentRepository;
  @Mock private TagRepository tagRepository;
  @Mock private NoteCollectionRepository noteCollectionRepository;
  @Mock private NoteRepository noteRepository;
  @Mock private FlashcardDeckRepository flashcardDeckRepository;
  @Mock private FlashcardRepository flashcardRepository;
  @Mock private ExamRepository examRepository;
  @Mock private QuestionRepository questionRepository;
  @Mock private AnswerRepository answerRepository;
  @Mock private ExamAttemptRepository examAttemptRepository;

  @Mock private AuthHelperService authHelperService;

  @InjectMocks private EnvironmentHelperMethods helperMethods;

  @Nested
  @DisplayName("EnsureEnvironmentExistsAndUserIsOwnerOrAdmin Tests")
  class EnsureEnvironmentExistsAndUserIsOwnerOrAdminTests {
    @Test
    void shouldNotThrowExceptionWhenUserIsOwner() {
      User user = getMockUser();
      when(authHelperService.getAuthenticatedUser()).thenReturn(user);
      when(environmentRepository.findById(VALID_ID))
          .thenReturn(Optional.of(getMockEnvironment(user)));

      helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID);

      verify(authHelperService).getAuthenticatedUser();

      assertDoesNotThrow(
          () -> helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID));
    }

    @Test
    void shouldNotThrowExceptionWhenUserIsAdmin() {
      when(authHelperService.getAuthenticatedUser()).thenReturn(getMockAdmin());
      when(environmentRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockEnvironment()));

      helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID);

      assertDoesNotThrow(
          () -> helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID));
    }

    @Test
    void shouldThrowNotFoundWhenGivenInvalidId() {
      User user = getMockUser();
      when(authHelperService.getAuthenticatedUser()).thenReturn(user);
      when(environmentRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(
              ItemNotFoundException.class,
              () -> helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(INVALID_ID));

      assertEquals("Environment not found", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalActionExceptionWhenUserIsNotOwner() {
      when(authHelperService.getAuthenticatedUser()).thenReturn(getMockUser());
      when(environmentRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockEnvironment()));

      IllegalActionException exception =
          assertThrows(
              IllegalActionException.class,
              () -> helperMethods.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(VALID_ID));

      assertEquals(
          "Only the owner or an administrator can perform this action.", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("throwIfIdMisMatch Tests")
  class ThrowIfIdMismatchTests {
    @Test
    void shouldNotThrowIfIdMatch() {
      assertDoesNotThrow(
          () ->
              helperMethods.throwIfIdMismatch(
                  VALID_ID, VALID_ID, new ExamAttemptSubmittedException("TEST")));
    }

    @Test
    void shouldThrowTheProvidedExceptionWhenIdMatch() {

      assertThrows(
          ExamAttemptSubmittedException.class,
          () ->
              helperMethods.throwIfIdMismatch(
                  VALID_ID, INVALID_ID, new ExamAttemptSubmittedException("TEST")));
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenMissingExpectedId() {
      assertThrows(
          MissingArgumentException.class,
          () ->
              helperMethods.throwIfIdMismatch(
                  null, INVALID_ID, new ExamAttemptSubmittedException("TEST")));
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenMissingActualId() {
      assertThrows(
          MissingArgumentException.class,
          () ->
              helperMethods.throwIfIdMismatch(
                  VALID_ID, null, new ExamAttemptSubmittedException("TEST")));
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenNoExceptionIsPassed() {
      assertThrows(
          MissingArgumentException.class,
          () -> helperMethods.throwIfIdMismatch(VALID_ID, VALID_ID, null));
    }
  }

  @Nested
  @DisplayName("throwIfNotInEnvironment Tests")
  class ThrowIfNotInEnvironmentTests {
    @Test
    void shouldNotThrowWhenMemberFromEnvironment() {
      Environment environment = getMockEnvironment();
      Exam exam = getMockExam(environment);
      assertDoesNotThrow(() -> helperMethods.throwIfNotInEnvironment(exam, environment.getId()));
    }

    @Test
    void shouldThrowResourceNotConnectedToParentExceptionWhenNotAMemberOfEnvironment() {
      Exam mockExam = getMockExam(getMockEnvironment());

      assertThrows(
          ResourceNotConnectedToParentException.class,
          () -> helperMethods.throwIfNotInEnvironment(mockExam, INVALID_ID));
    }

    @Test
    void shouldThrowMissingEnvironmentExceptionWhenEnvironmentMemberDoesNotHaveAnEnvironmentSet() {
      assertThrows(
          MissingEnvironmentException.class,
          () -> helperMethods.throwIfNotInEnvironment(getMockExam(), INVALID_ID));
    }
  }

  @Nested
  @DisplayName("throwIfExamIsFinalised Tests")
  class ThrowIfExamIsFinalisedTests {
    @Test
    void shouldThrowFinalisedExamExceptionIfExamIsFinalised() {
      Exam exam = getMockExam();
      exam.setFinalised(true);
      FinalisedExamException exception =
          assertThrows(
              FinalisedExamException.class, () -> helperMethods.throwIfExamIsFinalised(exam));

      assertEquals(
          "Altering an Exam or or it's related Questions and answers is not allowed after the Exam is finalised",
          exception.getMessage());
    }

    @Test
    void shouldNotThrowExceptionIfExamIsNotFinalised() {
      assertDoesNotThrow(() -> helperMethods.throwIfExamIsFinalised(getMockExam()));
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenExamIsNull() {
      assertThrows(
          MissingArgumentException.class, () -> helperMethods.throwIfExamIsFinalised(null));
    }
  }

  @Nested
  @DisplayName("getEnvironmentOrThrow Tests")
  class GetEnvironmentOrThrowTests {
    @Test
    void shouldReturnEnvironmentWhenFound() {
      when(environmentRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockEnvironment()));

      Environment result = helperMethods.getEnvironmentOrThrow(VALID_ID);

      assertNotNull(result);
    }

    @Test
    void shouldThrowNotFoundWhenEnvironmentNotFound() {
      when(environmentRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(
              ItemNotFoundException.class, () -> helperMethods.getEnvironmentOrThrow(INVALID_ID));

      assertEquals("Environment not found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenIdIsNull() {

      MissingArgumentException exception =
          assertThrows(
              MissingArgumentException.class, () -> helperMethods.getEnvironmentOrThrow(null));

      assertEquals("Can not retrieve environment, ID is NULL", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("getTagOrThrow Tests")
  class GetTagOrThrowTests {
    @Test
    void shouldReturnTagWhenFound() {
      when(tagRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockTag()));

      Tag tag = helperMethods.getTagOrThrow(VALID_ID);

      assertNotNull(tag);
      assertEquals("Amazing Tag", tag.getTitle());
      assertEquals("Tags related to Amazing subjects", tag.getDescription());
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenNotFound() {
      when(tagRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(ItemNotFoundException.class, () -> helperMethods.getTagOrThrow(INVALID_ID));

      assertEquals("Tag not found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenIdIsNull() {
      MissingArgumentException exception =
          assertThrows(MissingArgumentException.class, () -> helperMethods.getTagOrThrow(null));

      assertEquals("Can not retrieve tag, ID is NULL", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("getNoteCollectionOrThrow Tests")
  class GetNoteCollectionOrThrowTests {
    @Test
    void shouldReturnNoteCollectionWhenFound() {
      when(noteCollectionRepository.findById(VALID_ID))
          .thenReturn(Optional.of(getMockNoteCollection()));

      NoteCollection noteCollection = helperMethods.getNoteCollectionOrThrow(VALID_ID);

      assertNotNull(noteCollection);
      assertEquals("Collection of Notes", noteCollection.getTitle());
      assertEquals("All about Collections!", noteCollection.getDescription());
    }

    @Test
    void shouldThrowItemNotFoundWhenNoteCollectionNotFound() {
      when(noteCollectionRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(
              ItemNotFoundException.class,
              () -> helperMethods.getNoteCollectionOrThrow(INVALID_ID));

      assertEquals("Note Collection not found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenIdIsNull() {
      MissingArgumentException exception =
          assertThrows(
              MissingArgumentException.class, () -> helperMethods.getNoteCollectionOrThrow(null));

      assertEquals("Can not retrieve NoteCollection, ID is NULL", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("getNoteOrThrow Tests")
  class GetNoteOrThrowTests {
    @Test
    void shouldReturnNote() {
      when(noteRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockNote()));

      Note note = helperMethods.getNoteOrThrow(VALID_ID);

      assertNotNull(note);
      assertEquals("Noteworthy", note.getTitle());
      assertEquals("Worth taking note of", note.getBody());
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenInvalidIdIsGiven() {
      when(noteRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(ItemNotFoundException.class, () -> helperMethods.getNoteOrThrow(INVALID_ID));

      assertEquals("Note not found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenIdIsNull() {
      MissingArgumentException exception =
          assertThrows(MissingArgumentException.class, () -> helperMethods.getNoteOrThrow(null));

      assertEquals("Can not retrieve note, ID is NULL", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("getFlashcardDeckOrThrow Tests")
  class GetFlashcardDeckOrThrowTests {
    @Test
    void shouldReturnFlashcardDeck() {
      when(flashcardDeckRepository.findById(VALID_ID))
          .thenReturn(Optional.of(getMockFlashcardDeck()));

      FlashcardDeck flashcardDeck = helperMethods.getFlashcardDeckOrThrow(VALID_ID);

      assertNotNull(flashcardDeck);
      assertEquals("Shiny Flashcard Deck", flashcardDeck.getTitle());
      assertEquals("Fancy, Shiny and brand new", flashcardDeck.getDescription());
    }

    @Test
    void shouldThrowItemNotFoundWhenGivenInvalidId() {
      when(flashcardDeckRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(
              ItemNotFoundException.class, () -> helperMethods.getFlashcardDeckOrThrow(INVALID_ID));

      assertEquals("Flashcard deck not found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenIdIsNull() {
      MissingArgumentException exception =
          assertThrows(
              MissingArgumentException.class, () -> helperMethods.getFlashcardDeckOrThrow(null));

      assertEquals("Can not retrieve FlashcardDeck, ID is NULL", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("getFlashcardOrThrow Tests")
  class GetFlashcardOrThrowTests {
    @Test
    void shouldReturnFlashcard() {
      when(flashcardRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockFlashcard()));

      Flashcard flashcard = helperMethods.getFlashcardOrThrow(VALID_ID);

      assertNotNull(flashcard);
      assertEquals("Question", flashcard.getFrontBody());
      assertEquals("Answer", flashcard.getBackBody());
    }

    @Test
    void shouldThrowItemNotFoundGivenInvalidId() {
      when(flashcardRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(
              ItemNotFoundException.class, () -> helperMethods.getFlashcardOrThrow(INVALID_ID));

      assertEquals("Flashcard not found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenIdIsNull() {
      MissingArgumentException exception =
          assertThrows(
              MissingArgumentException.class, () -> helperMethods.getFlashcardOrThrow(null));

      assertEquals("Could not retrieve Flashcard, ID is NULL", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("getExamOrThrow Tests")
  class GetExamOrThrowTests {
    @Test
    void shouldReturnExam() {
      when(examRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockExam()));

      Exam exam = helperMethods.getExamOrThrow(VALID_ID);

      assertNotNull(exam);
    }

    @Test
    void shouldThrowItemNotFoundExceptionGivenInvalidId() {
      when(examRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(ItemNotFoundException.class, () -> helperMethods.getExamOrThrow(INVALID_ID));

      assertEquals("Exam not found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenIdIsNull() {
      MissingArgumentException exception =
          assertThrows(MissingArgumentException.class, () -> helperMethods.getExamOrThrow(null));

      assertEquals("Could not retrieve Exam, ID is NULL", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("getExamAttemptOrThrow Tests")
  class GetExamAttemptOrThrowTests {
    @Test
    void shouldReturnExamAttempt() {
      when(examAttemptRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockExamAttempt()));

      ExamAttempt attempt = helperMethods.getExamAttemptOrThrow(VALID_ID);

      assertNotNull(attempt);
    }

    @Test
    void shouldThrowItemNotFoundWhenGivenInvalidId() {
      when(examAttemptRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(
              ItemNotFoundException.class, () -> helperMethods.getExamAttemptOrThrow(INVALID_ID));

      assertEquals("Exam-attempt not Found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenIdIsNull() {

      MissingArgumentException exception =
          assertThrows(
              MissingArgumentException.class, () -> helperMethods.getExamAttemptOrThrow(null));

      assertEquals("Could not retrieve Exam-attempt, ID is NULL", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("getQuestionOrThrow Tests")
  class GetQuestionOrThrowTests {
    @Test
    void shouldReturnQuestion() {
      when(questionRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockQuestion()));

      Question question = helperMethods.getQuestionOrThrow(VALID_ID);

      assertNotNull(question);
      assertEquals("Wat is?", question.getQuestion());
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenGivenInvalidId() {
      when(questionRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(
              ItemNotFoundException.class, () -> helperMethods.getQuestionOrThrow(INVALID_ID));

      assertEquals("Question not found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentExceptionWhenIdIsNull() {

      MissingArgumentException exception =
          assertThrows(
              MissingArgumentException.class, () -> helperMethods.getQuestionOrThrow(null));

      assertEquals("Could not retrieve Question, ID is NULL", exception.getMessage());
    }
  }

  @Nested
  @DisplayName("getAnswerOrThrow Tests")
  class GetAnswerOrThrowTests {
    @Test
    void shouldReturnAnswer() {
      when(answerRepository.findById(VALID_ID)).thenReturn(Optional.of(getMockAnswer()));

      Answer answer = helperMethods.getAnswerOrThrow(VALID_ID);

      assertNotNull(answer);
      assertEquals("Is it a", answer.getAnswer());
    }

    @Test
    void shouldThrowItemNotFoundWhenGivenInvalidId() {
      when(answerRepository.findById(INVALID_ID)).thenReturn(Optional.empty());

      ItemNotFoundException exception =
          assertThrows(
              ItemNotFoundException.class, () -> helperMethods.getAnswerOrThrow(INVALID_ID));

      assertEquals("Answer not found", exception.getMessage());
    }

    @Test
    void shouldThrowMissingArgumentWhenIdIsNull() {
      MissingArgumentException exception =
          assertThrows(MissingArgumentException.class, () -> helperMethods.getAnswerOrThrow(null));

      assertEquals("Could not retrieve Answer, ID is NULL", exception.getMessage());
    }
  }
}
