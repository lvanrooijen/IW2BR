package com.bella.IW2BR.domain.flashcarddeck.flashcard;

import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.GetFlashcard;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.PatchFlashcard;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.dto.PostFlashcard;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.exceptions.generic.ResourceNotConnectedToParentException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardService {
  private final FlashcardRepository flashcardRepository;
  private final EnvironmentHelperMethods environmentHelper;

  public GetFlashcard create(Long environmentId, Long flashcardDeckId, PostFlashcard body) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    FlashcardDeck deck = environmentHelper.getFlashcardDeckOrThrow(flashcardDeckId);
    environmentHelper.throwIfNotInEnvironment(deck, environmentId);

    Flashcard flashcard;
    if (body.tagId() != null) {
      Tag tag = environmentHelper.getTagOrThrow(body.tagId());
      flashcard = PostFlashcard.from(body, deck, tag);
    } else {
      flashcard = PostFlashcard.from(body, deck);
    }

    flashcardRepository.save(flashcard);
    return GetFlashcard.to(flashcard);
  }

  public GetFlashcard getById(Long environmentId, Long flashcardDeckId, Long id) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);
    Flashcard flashcard = environmentHelper.getFlashcardOrThrow(id);

    throwIfFlashcardDeckIdMismatch(flashcardDeckId, flashcard);

    environmentHelper.throwIfNotInEnvironment(flashcard.getFlashcardDeck(), environmentId);

    return GetFlashcard.to(flashcard);
  }

  public List<GetFlashcard> getAll(Long environmentId, Long flashcardDeckId) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    FlashcardDeck flashcardDeck = environmentHelper.getFlashcardDeckOrThrow(flashcardDeckId);
    environmentHelper.throwIfNotInEnvironment(flashcardDeck, environmentId);

    List<Flashcard> flashcards = flashcardRepository.findByFlashcardDeckId(flashcardDeckId);

    return flashcards.stream().map(GetFlashcard::to).toList();
  }

  public GetFlashcard update(
      Long environmentId, Long flashcardDeckId, Long id, PatchFlashcard patch) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Flashcard flashcard = environmentHelper.getFlashcardOrThrow(id);
    environmentHelper.throwIfNotInEnvironment(flashcard.getFlashcardDeck(), environmentId);

    throwIfFlashcardDeckIdMismatch(flashcardDeckId, flashcard);

    if (patch.tagId() != null) {
      Tag tag = environmentHelper.getTagOrThrow(patch.tagId());
      PatchFlashcard.patch(flashcard, patch, tag);
    } else {
      PatchFlashcard.patch(flashcard, patch);
    }

    flashcardRepository.save(flashcard);
    return GetFlashcard.to(flashcard);
  }

  public void delete(Long environmentId, Long flashcardDeckId, Long id) {
    environmentHelper.ensureEnvironmentExistsAndUserIsOwnerOrAdmin(environmentId);

    Flashcard flashcard = environmentHelper.getFlashcardOrThrow(id);
    environmentHelper.throwIfNotInEnvironment(flashcard.getFlashcardDeck(), environmentId);

    throwIfFlashcardDeckIdMismatch(flashcardDeckId, flashcard);

    flashcardRepository.deleteById(id);
  }

  /**
   * Verifies if the flashcard deck in the path variable is the same as the flashcard-deck connected
   * to the flashcard
   *
   * @param flashcardDeckId ID of flashcard provided through the path variable
   * @param flashcard {@link Flashcard}
   * @throws ResourceNotConnectedToParentException when the ID's aren't the same
   */
  private void throwIfFlashcardDeckIdMismatch(Long flashcardDeckId, Flashcard flashcard) {
    environmentHelper.throwIfIdMismatch(
        flashcardDeckId,
        flashcard.getFlashcardDeck().getId(),
        new ResourceNotConnectedToParentException(
            "Flashcard is not a member of the flashcard deck specified in the path variable"));
  }
}
