package com.bella.IW2BR.domain.flashcarddeck.flashcard;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
  List<Flashcard> findByFlashcardDeckId(Long flashcardDeckId);
}
