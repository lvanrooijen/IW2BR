package com.bella.IW2BR.domain.flashcard.dto;

import com.bella.IW2BR.domain.flashcard.Flashcard;
import com.bella.IW2BR.domain.flashcard.FlashcardFlag;
import com.bella.IW2BR.domain.flashcarddeck.FlashcardDeck;
import com.bella.IW2BR.domain.tag.Tag;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class FlashcardMapper {
  public GetFlashcard toGet(Flashcard flashcard) {
    Long tagId = flashcard.getTag() == null ? null : flashcard.getTag().getId();

    return new GetFlashcard(
        flashcard.getId(),
        flashcard.getFrontBody(),
        flashcard.getBackBody(),
        flashcard.getFlashcardDeck().getId(),
        tagId);
  }

  public Flashcard fromPost(PostFlashcard dto, FlashcardDeck flashcardDeck) {
    return Flashcard.builder()
        .frontBody(dto.frontBody())
        .backBody(dto.backBody())
        .positiveFlags(0)
        .negativeFlags(0)
        .flashcardDeck(flashcardDeck)
        .build();
  }

  public Flashcard fromPost(PostFlashcard dto, FlashcardDeck flashcardDeck, Tag tag) {
    Flashcard flashcard = fromPost(dto, flashcardDeck);
    flashcard.setTag(tag);

    return flashcard;
  }

  public Flashcard updateFields(Flashcard entity, PatchFlashcard dto) {
    if (dto.frontBody() != null) {
      entity.setFrontBody(dto.frontBody());
    }
    if (dto.backBody() != null) {
      entity.setBackBody(dto.backBody());
    }
    return entity;
  }

  public Flashcard updateFields(Flashcard entity, PatchFlashcard dto, Tag tag) {
    updateFields(entity, dto);
    entity.setTag(tag);
    return entity;
  }

  /**
   * increments the flashcards positive or negative flags and updates the last seen date
   *
   * @param entity Flashcard
   * @param dto {@link UpdateFlashcardFlag}
   * @return {@link Flashcard}
   */
  public Flashcard flagFlashcard(Flashcard entity, UpdateFlashcardFlag dto) {
    if (dto.flag() == FlashcardFlag.POSITIVE) {
      entity.setPositiveFlags(entity.getPositiveFlags() + 1);
    }
    if (dto.flag() == FlashcardFlag.NEGATIVE) {
      entity.setNegativeFlags(entity.getNegativeFlags() + 1);
    }

    entity.setLastSeen(LocalDate.now());

    return entity;
  }
}
