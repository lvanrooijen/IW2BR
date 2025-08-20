package com.bella.IW2BR.domain.flashcarddeck.deck.dto;

/**
 * DTO representing how a Flashcard Deck is returned to the client
 *
 * @param id
 * @param title
 * @param description
 */
public record GetFlashcardDeck(Long id, String title, String description) {}
