package com.bella.IW2BR.domain.environment.dto;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.exam.attempt.ExamAttempt;
import com.bella.IW2BR.domain.exam.attempt.dto.GetAttemptCompact;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.exam.exam.dto.GetExam;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.deck.dto.GetFlashcardDeck;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.dto.GetNoteCollection;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.domain.tag.dto.GetTagCompact;
import com.bella.IW2BR.domain.user.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service that handles mapping between Environment DTOs and the Environment entity.
 *
 * <p>Methods:
 *
 * <ul>
 *   <li>Map {@link PostEnvironment} DTO to {@link Environment} entity
 *   <li>Map {@link Environment} entity to {@link GetEnvironment}
 *   <li>Patch {@link Environment} with new data from {@link PatchEnvironment}
 * </ul>
 */
@Service
public class EnvironmentMapper {
  public Environment fromPost(PostEnvironment dto, User user) {
    return Environment.builder()
        .title(dto.title())
        .description(dto.description())
        .owner(user)
        .createdAt(LocalDate.now())
        .build();
  }

  public GetEnvironment toGet(Environment environment) {
    return new GetEnvironment(
        environment.getId(),
        environment.getTitle(),
        environment.getDescription(),
        environment.getCreatedAt());
  }

  public GetFullEnvironment toGetFull(Environment environment) {
    List<GetNoteCollection> noteCollections =
        environment.getNoteCollections().stream().map(this::mapNoteCollection).toList();

    List<GetFlashcardDeck> flashcardDecks =
        environment.getFlashcardDecks().stream().map(this::mapFlashcardDeck).toList();

    List<GetExam> exams = environment.getExams().stream().map(this::mapExam).toList();

    List<GetAttemptCompact> attempts =
        environment.getExamAttempts().stream().map(this::mapExamAttempt).toList();

    List<GetTagCompact> tags = environment.getTags().stream().map(this::mapTag).toList();

    return new GetFullEnvironment(
        environment.getId(),
        environment.getTitle(),
        environment.getDescription(),
        noteCollections,
        flashcardDecks,
        exams,
        attempts,
        tags);
  }

  public Environment updateFields(Environment environment, PatchEnvironment patch) {
    if (patch.title() != null) {
      environment.setTitle(patch.title());
    }
    if (patch.description() != null) {
      environment.setDescription(patch.description());
    }
    return environment;
  }

  private GetNoteCollection mapNoteCollection(NoteCollection collection) {
    return new GetNoteCollection(
        collection.getId(), collection.getTitle(), collection.getDescription());
  }

  private GetFlashcardDeck mapFlashcardDeck(FlashcardDeck deck) {
    return new GetFlashcardDeck(deck.getId(), deck.getTitle(), deck.getDescription());
  }

  private GetExam mapExam(Exam exam) {
    return new GetExam(exam.getId(), exam.getTitle(), exam.getDescription());
  }

  private GetAttemptCompact mapExamAttempt(ExamAttempt attempt) {
    return new GetAttemptCompact(
        attempt.getId(), attempt.getExam().getTitle(), attempt.getScore(), attempt.isCompleted());
  }

  private GetTagCompact mapTag(Tag tag) {
    return new GetTagCompact(tag.getId(), tag.getTitle());
  }
}
