package com.bella.IW2BR.domain.environment;

import com.bella.IW2BR.domain.exam.answer.Answer;
import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.exam.question.Question;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.Flashcard;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.note.Note;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.domain.user.Role;
import com.bella.IW2BR.domain.user.User;
import java.time.LocalDate;
import java.util.UUID;

public class EnvironmentTestConstants {
  public static final Long VALID_ID = 1L;
  public static final Long INVALID_ID = -1L;
  public static final String ENVIRONMENT_TITLE = "Math";
  public static final String ENVIRONMENT_DESCRIPTION = "1+1=2";

  public static User getMockUser() {
    User user =
        User.builder()
            .firstName("user")
            .lastName("regular")
            .email("user@email.com")
            .role(Role.USER)
            .build();
    user.setId(UUID.randomUUID());
    return user;
  }

  public static User getMockAdmin() {
    User user = getMockUser();
    user.setRole(Role.ADMIN);
    return user;
  }

  public static Environment getMockEnvironment() {
    return Environment.builder()
        .title(ENVIRONMENT_TITLE)
        .description(ENVIRONMENT_DESCRIPTION)
        .owner(getMockUser())
        .createdAt(LocalDate.now())
        .build();
  }

  /**
   * Creates an Environment, provided user will be set as owner
   *
   * @param owner {@link User} representing the environments owner
   * @return {@link Environment} Mock Environment
   */
  public static Environment getMockEnvironment(User owner) {
    return Environment.builder()
        .title(ENVIRONMENT_TITLE)
        .description(ENVIRONMENT_DESCRIPTION)
        .owner(owner)
        .createdAt(LocalDate.now())
        .build();
  }

  public static Exam getMockExam() {
    return Exam.builder()
        .title("The Initial Exam")
        .description("My First Exam!")
        .createdAt(LocalDate.now())
        .build();
  }

  /**
   * Overloaded Method, sets the {@link Exam} as a member of the provided {@link Environment}
   *
   * @param environment
   * @return {@link Exam}
   */
  public static Exam getMockExam(Environment environment) {
    Exam exam = getMockExam();
    exam.setEnvironment(environment);
    return exam;
  }

  public static Tag getMockTag() {
    return Tag.builder()
        .title("Amazing Tag")
        .description("Tags related to Amazing subjects")
        .build();
  }

  public static NoteCollection getMockNoteCollection() {
    return NoteCollection.builder()
        .title("Collection of Notes")
        .description("All about Collections!")
        .build();
  }

  public static Note getMockNote() {
    return Note.builder().title("Noteworthy").body("Worth taking note of").build();
  }

  public static FlashcardDeck getMockFlashcardDeck() {
    return FlashcardDeck.builder()
        .title("Shiny Flashcard Deck")
        .description("Fancy, Shiny and brand new")
        .build();
  }

  public static Flashcard getMockFlashcard() {
    return Flashcard.builder().frontBody("Question").backBody("Answer").build();
  }

  public static ExamAttempt getMockExamAttempt() {
    return ExamAttempt.builder().isCompleted(false).build();
  }

  public static Question getMockQuestion() {
    return Question.builder().question("Wat is?").build();
  }

  public static Answer getMockAnswer() {
    return Answer.builder().answer("Is it a").build();
  }
}
