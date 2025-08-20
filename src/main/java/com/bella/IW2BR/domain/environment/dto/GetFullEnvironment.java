package com.bella.IW2BR.domain.environment.dto;

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
    List<GetTagCompact> tags) {}
