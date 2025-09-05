package com.bella.IW2BR.domain.flashcarddeck.deck;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.flashcarddeck.deck.dto.GetFlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.deck.dto.PatchFlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.deck.dto.PostFlashcardDeck;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardDeckService {
  private final EnvironmentHelperMethods environmentHelper;
  private final FlashcardDeckRepository flashcardDeckRepository;

  public GetFlashcardDeck create(Long environmentId, PostFlashcardDeck body) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Environment environment = environmentHelper.getEnvironmentOrThrow(environmentId);

    FlashcardDeck flashcardDeck = PostFlashcardDeck.from(body, environment);

    flashcardDeckRepository.save(flashcardDeck);

    return GetFlashcardDeck.to(flashcardDeck);
  }

  public GetFlashcardDeck getById(Long environmentId, Long id) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    FlashcardDeck flashcardDeck = environmentHelper.getFlashcardDeckOrThrow(id);

    environmentHelper.throwIfNotInEnvironment(flashcardDeck, environmentId);

    return GetFlashcardDeck.to(flashcardDeck);
  }

  public List<GetFlashcardDeck> getAll(Long environmentId) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    List<FlashcardDeck> flashcardDecks = flashcardDeckRepository.findByEnvironmentId(environmentId);

    return flashcardDecks.stream().map(GetFlashcardDeck::to).toList();
  }

  public GetFlashcardDeck update(Long environmentId, Long id, PatchFlashcardDeck patch) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    FlashcardDeck flashcardDeck = environmentHelper.getFlashcardDeckOrThrow(id);
    environmentHelper.throwIfNotInEnvironment(flashcardDeck, environmentId);

    PatchFlashcardDeck.patch(flashcardDeck, patch);

    flashcardDeckRepository.save(flashcardDeck);
    return GetFlashcardDeck.to(flashcardDeck);
  }

  public void delete(Long environmentId, Long id) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    environmentHelper.getFlashcardDeckOrThrow(id);
    flashcardDeckRepository.deleteById(id);
  }
}
