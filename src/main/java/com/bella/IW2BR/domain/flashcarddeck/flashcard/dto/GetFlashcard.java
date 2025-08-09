package com.bella.IW2BR.domain.flashcarddeck.flashcard.dto;

/**
 * DTO representing how the Flashcard is returned to the client
 *
 * @param id
 * @param frontBody
 * @param backBody
 * @param flashcardDeckId
 * @param tagId
 */
public record GetFlashcard(
    Long id, String frontBody, String backBody, Long flashcardDeckId, Long tagId) {}
