package com.bella.IW2BR.domain.flashcarddeck.dto;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.flashcarddeck.FlashcardDeck;
import com.bella.IW2BR.domain.tag.Tag;
import org.springframework.stereotype.Service;

@Service
public class FlashcardDeckMapper {
  public GetFlashcardDeck toGet(FlashcardDeck entity) {
    Long tagId = entity.getTag() != null ? entity.getTag().getId() : null;
    return new GetFlashcardDeck(
        entity.getId(),
        entity.getTitle(),
        entity.getDescription(),
        entity.getEnvironment().getId(),
        tagId);
  }

  public FlashcardDeck fromPost(PostFlashcardDeck dto, Environment environment) {
    return FlashcardDeck.builder()
        .title(dto.title())
        .description(dto.description())
        .environment(environment)
        .build();
  }

  public FlashcardDeck fromPost(PostFlashcardDeck dto, Environment environment, Tag tag) {
    FlashcardDeck flashcardDeck = fromPost(dto, environment);
    flashcardDeck.setTag(tag);
    return flashcardDeck;
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

  public FlashcardDeck updateFields(FlashcardDeck entity, PatchFlashcardDeck patch, Tag tag) {
    updateFields(entity, patch);
    entity.setTag(tag);
    return entity;
  }
}
