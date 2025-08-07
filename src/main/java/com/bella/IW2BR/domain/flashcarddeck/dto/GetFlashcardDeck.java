package com.bella.IW2BR.domain.flashcarddeck.dto;

public record GetFlashcardDeck(
    Long id, String title, String description, Long environmentId, Long tagId) {}
