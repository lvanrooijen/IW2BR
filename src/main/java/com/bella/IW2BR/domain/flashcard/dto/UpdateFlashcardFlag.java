package com.bella.IW2BR.domain.flashcard.dto;

import com.bella.IW2BR.domain.flashcard.FlashcardFlag;
import jakarta.validation.constraints.NotNull;

/**
 * DTO representing the request body used as a PATCH request for updating the flag of a flashcard
 *
 * @param flag Options: NEGATIVE, POSITIVE
 */
public record UpdateFlashcardFlag(@NotNull FlashcardFlag flag) {}
