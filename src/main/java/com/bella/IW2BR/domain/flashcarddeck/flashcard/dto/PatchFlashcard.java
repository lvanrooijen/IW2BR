package com.bella.IW2BR.domain.flashcarddeck.flashcard.dto;

import static com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.FlashcardConstraints.*;
import static com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.FlashcardConstraints.BACK_BODY_MAX;
import static com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.FlashcardConstraints.BODY_MIN;
import static com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.FlashcardConstraints.INVALID_BACK_BODY_LENGTH_MSG;

import com.bella.IW2BR.domain.flashcarddeck.flashcard.Flashcard;
import com.bella.IW2BR.domain.tag.Tag;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;

/**
 * DTO representing the request body of a Flashcard PATCH request
 *
 * @param frontBody
 * @param backBody
 * @param tagId
 */
public record PatchFlashcard(
    @Length(min = BODY_MIN, max = FRONT_BODY_MAX, message = INVALID_FRONT_BODY_LENGTH_MSG)
        String frontBody,
    @Length(min = BODY_MIN, max = BACK_BODY_MAX, message = INVALID_BACK_BODY_LENGTH_MSG)
        String backBody,
    @Positive Long tagId,
    Boolean flag) {
  /**
   * Updates Fields of {@link Flashcard}
   *
   * @param entity {@link Flashcard}
   * @param dto {@link GetFlashcard}
   * @return {@link GetFlashcard}
   */
  public static Flashcard patch(Flashcard entity, PatchFlashcard dto) {
    if (dto.frontBody() != null) {
      entity.setFrontBody(dto.frontBody());
    }
    if (dto.backBody() != null) {
      entity.setBackBody(dto.backBody());
    }
    if (dto.flag() != null) {
      if (dto.flag()) {
        entity.setPositiveFlags(entity.getPositiveFlags() + 1);
      }
      if (!dto.flag()) {
        entity.setNegativeFlags(entity.getNegativeFlags() + 1);
      }
      entity.setLastSeen(LocalDate.now());
    }
    return entity;
  }

  /**
   * Updates Fields of {@link Flashcard}
   *
   * @param entity {@link Flashcard}
   * @param dto {@link GetFlashcard}
   * @param tag {@link Tag}
   * @return updated {@link Flashcard}
   */
  public static Flashcard patch(Flashcard entity, PatchFlashcard dto, Tag tag) {
    patch(entity, dto);
    entity.setTag(tag);
    return entity;
  }
}
