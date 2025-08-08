package com.bella.IW2BR.domain.flashcarddeck.dto;

/**
 * DTO representing how a Flashcard Deck is returned to the client
 *
 * @param id
 * @param title
 * @param description
 * @param environmentId
 */
public record GetFlashcardDeck(Long id, String title, String description, Long environmentId) {}
