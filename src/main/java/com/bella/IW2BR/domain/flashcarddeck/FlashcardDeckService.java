package com.bella.IW2BR.domain.flashcarddeck;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.flashcarddeck.dto.FlashcardDeckMapper;
import com.bella.IW2BR.domain.flashcarddeck.dto.GetFlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.dto.PatchFlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.dto.PostFlashcardDeck;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardDeckService {
  private final EnvironmentHelperMethods environmentHelper;
  private final FlashcardDeckRepository flashcardDeckRepository;
  private final FlashcardDeckMapper mapper;

  public GetFlashcardDeck createFlashcardDeck(Long environmentId, PostFlashcardDeck body) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);

    Environment environment = environmentHelper.getEnvironmentOrThrow(environmentId);

    FlashcardDeck flashcardDeck = mapper.fromPost(body, environment);

    flashcardDeckRepository.save(flashcardDeck);

    return mapper.toGet(flashcardDeck);
  }

  public GetFlashcardDeck getFlashcardDeckById(Long environmentId, Long id) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);

    FlashcardDeck flashcardDeck = environmentHelper.getFlashcardDeckOrThrow(id);

    environmentHelper.throwIfNotInEnvironment(flashcardDeck, environmentId);

    return mapper.toGet(flashcardDeck);
  }

  public List<GetFlashcardDeck> getAllFlashcardDecks(Long environmentId) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);

    List<FlashcardDeck> flashcardDecks = flashcardDeckRepository.findByEnvironmentId(environmentId);

    return flashcardDecks.stream().map(mapper::toGet).toList();
  }

  public GetFlashcardDeck updateFlashcardDeck(
      Long environmentId, Long id, PatchFlashcardDeck patch) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);

    FlashcardDeck flashcardDeck = environmentHelper.getFlashcardDeckOrThrow(id);
    environmentHelper.throwIfNotInEnvironment(flashcardDeck, environmentId);

    mapper.updateFields(flashcardDeck, patch);

    flashcardDeckRepository.save(flashcardDeck);

    return mapper.toGet(flashcardDeck);
  }

  public void deleteFlashcardDeck(Long environmentId, Long id) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);
    environmentHelper.getFlashcardDeckOrThrow(id);
    flashcardDeckRepository.deleteById(id);
  }
}
