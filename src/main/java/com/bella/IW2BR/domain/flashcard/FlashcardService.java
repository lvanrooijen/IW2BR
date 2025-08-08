package com.bella.IW2BR.domain.flashcard;

import com.bella.IW2BR.domain.environment.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.flashcard.dto.FlashcardMapper;
import com.bella.IW2BR.domain.flashcard.dto.GetFlashcard;
import com.bella.IW2BR.domain.flashcard.dto.PatchFlashcard;
import com.bella.IW2BR.domain.flashcard.dto.PostFlashcard;
import com.bella.IW2BR.domain.flashcarddeck.FlashcardDeck;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.exceptions.environment.FlashcardDeckMismatchException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardService {
  private final FlashcardRepository flashcardRepository;
  private final EnvironmentHelperMethods environmentHelper;
  private final FlashcardMapper mapper;

  public GetFlashcard create(Long environmentId, Long flashcardDeckId, PostFlashcard body) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);

    FlashcardDeck deck = environmentHelper.getFlashcardDeckOrThrow(flashcardDeckId);
    environmentHelper.throwIfNotInEnvironment(deck, environmentId);

    Flashcard flashcard;
    if (body.tagId() != null) {
      Tag tag = environmentHelper.getTagOrThrow(body.tagId());
      flashcard = mapper.fromPost(body, deck, tag);
    } else {
      flashcard = mapper.fromPost(body, deck);
    }

    flashcardRepository.save(flashcard);
    return mapper.toGet(flashcard);
  }

  public GetFlashcard getById(Long environmentId, Long flashcardDeckId, Long id) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);
    Flashcard flashcard = environmentHelper.getFlashcardOrThrow(id);

    throwIfFlashcardDeckIdMismatch(flashcardDeckId, flashcard);

    environmentHelper.throwIfNotInEnvironment(flashcard.getFlashcardDeck(), environmentId);

    return mapper.toGet(flashcard);
  }

  public List<GetFlashcard> getAll(Long environmentId, Long flashcardDeckId) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);

    FlashcardDeck flashcardDeck = environmentHelper.getFlashcardDeckOrThrow(flashcardDeckId);
    environmentHelper.throwIfNotInEnvironment(flashcardDeck, environmentId);

    List<Flashcard> flashcards = flashcardRepository.findByFlashcardDeckId(flashcardDeckId);

    return flashcards.stream().map(mapper::toGet).toList();
  }

  public GetFlashcard update(
      Long environmentId, Long flashcardDeckId, Long id, PatchFlashcard patch) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);

    Flashcard flashcard = environmentHelper.getFlashcardOrThrow(id);
    environmentHelper.throwIfNotInEnvironment(flashcard.getFlashcardDeck(), environmentId);

    throwIfFlashcardDeckIdMismatch(flashcardDeckId, flashcard);

    if (patch.tagId() != null) {
      Tag tag = environmentHelper.getTagOrThrow(patch.tagId());
      mapper.updateFields(flashcard, patch, tag);
    } else {
      mapper.updateFields(flashcard, patch);
    }

    flashcardRepository.save(flashcard);
    return mapper.toGet(flashcard);
  }

  public void delete(Long environmentId, Long flashcardDeckId, Long id) {
    environmentHelper.throwIfNotOwnerOrAdmin(environmentId);

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
   * @throws FlashcardDeckMismatchException when the ID's aren't the same
   */
  private void throwIfFlashcardDeckIdMismatch(Long flashcardDeckId, Flashcard flashcard) {
    environmentHelper.throwIfIdMismatch(
        flashcardDeckId,
        flashcard.getFlashcardDeck().getId(),
        new FlashcardDeckMismatchException(
            "Flashcard is not a member of the flashcard deck specified in the path variable"));
  }
}
