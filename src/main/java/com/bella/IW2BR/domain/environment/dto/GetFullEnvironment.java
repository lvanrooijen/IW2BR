package com.bella.IW2BR.domain.environment.dto;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.exam.attempt.dto.GetAttemptCompact;
import com.bella.IW2BR.domain.exam.exam.dto.GetExam;
import com.bella.IW2BR.domain.flashcarddeck.deck.dto.GetFlashcardDeck;
import com.bella.IW2BR.domain.notecollection.collection.dto.GetNoteCollection;
import com.bella.IW2BR.domain.tag.dto.GetTagCompact;
import java.util.List;

public record GetFullEnvironment(
    Long id,
    String title,
    String description,
    List<GetNoteCollection> noteCollections,
    List<GetFlashcardDeck> flashcardDecks,
    List<GetExam> exams,
    List<GetAttemptCompact> examAttempts,
    List<GetTagCompact> tags) {
  /**
   * Maps {@link Environment} to {@link GetFullEnvironment}
   *
   * @param environment {@link Environment}
   * @return {@link GetFullEnvironment}
   */
  public static GetFullEnvironment to(Environment environment) {
    List<GetNoteCollection> noteCollections =
        environment.getNoteCollections().stream().map(GetNoteCollection::to).toList();

    List<GetFlashcardDeck> flashcardDecks =
        environment.getFlashcardDecks().stream().map(GetFlashcardDeck::to).toList();

    List<GetExam> exams = environment.getExams().stream().map(GetExam::to).toList();

    List<GetAttemptCompact> attempts =
        environment.getExamAttempts().stream().map(GetAttemptCompact::to).toList();

    List<GetTagCompact> tags = environment.getTags().stream().map(GetTagCompact::to).toList();

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
}
