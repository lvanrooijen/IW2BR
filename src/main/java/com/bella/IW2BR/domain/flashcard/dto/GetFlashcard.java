package com.bella.IW2BR.domain.flashcard.dto;

public record GetFlashcard(
    Long id, String frontBody, String backBody, Long flashcardDeckId, Long tagId) {}
