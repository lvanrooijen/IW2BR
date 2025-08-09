package com.bella.IW2BR.domain.flashcarddeck.deck;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardDeckRepository extends JpaRepository<FlashcardDeck, Long> {
  public List<FlashcardDeck> findByEnvironmentId(Long environmentId);
}
