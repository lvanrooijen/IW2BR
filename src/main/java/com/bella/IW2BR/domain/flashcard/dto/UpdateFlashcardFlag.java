package com.bella.IW2BR.domain.flashcard.dto;

import com.bella.IW2BR.domain.flashcard.FlashcardFlag;
import jakarta.validation.constraints.NotNull;

public record UpdateFlashcardFlag(@NotNull FlashcardFlag flag) {}
