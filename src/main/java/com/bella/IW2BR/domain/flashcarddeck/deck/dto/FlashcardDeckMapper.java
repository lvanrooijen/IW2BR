package com.bella.IW2BR.domain.flashcarddeck.deck.dto;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import org.springframework.stereotype.Service;

/**
 * Service that handles mapping between DTOs and the entity.
 *
 * <p>Methods:
 *
 * <ul>
 *   <li>Map {@link PostFlashcardDeck} DTO to {@link FlashcardDeck} entity
 *   <li>Map {@link FlashcardDeck} entity to {@link GetFlashcardDeck}
 *   <li>Patch {@link FlashcardDeck} with new data from {@link PatchFlashcardDeck}
 * </ul>
 */
@Service
public class FlashcardDeckMapper {
  public GetFlashcardDeck toGet(FlashcardDeck entity) {
    return new GetFlashcardDeck(entity.getId(), entity.getTitle(), entity.getDescription());
  }

  public FlashcardDeck fromPost(PostFlashcardDeck dto, Environment environment) {
    return FlashcardDeck.builder()
        .title(dto.title())
        .description(dto.description())
        .environment(environment)
        .build();
  }

  public FlashcardDeck updateFields(FlashcardDeck entity, PatchFlashcardDeck patch) {
    if (patch.title() != null) {
      entity.setTitle(patch.title());
    }
    if (patch.description() != null) {
      entity.setDescription(patch.description());
    }
    return entity;
  }
}
