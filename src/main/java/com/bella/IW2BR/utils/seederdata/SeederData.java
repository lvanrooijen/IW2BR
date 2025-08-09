package com.bella.IW2BR.utils.seederdata;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.Flashcard;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.note.Note;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.domain.user.Role;
import com.bella.IW2BR.domain.user.User;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeederData {
  private final PasswordEncoder passwordEncoder;

  public List<User> getUsers() {
    User hannie =
        User.builder()
            .firstName("Hannie")
            .lastName("Schaft")
            .role(Role.USER)
            .email("hannie_schaft@email.com")
            .password(passwordEncoder.encode("SecurePassword123!"))
            .build();

    User admin =
        User.builder()
            .firstName("admin")
            .lastName("istrator")
            .role(Role.ADMIN)
            .email("admin@email.com")
            .password(passwordEncoder.encode("SecurePassword123!"))
            .build();

    return List.of(hannie, admin);
  }

  public List<Environment> getEnvironments() {
    Environment mathematics =
        Environment.builder()
            .title("Mathematics")
            .description("Learn how to add 1 and 1 and discover the outcome!")
            .createdAt(LocalDate.now())
            .build();

    Environment history =
        Environment.builder()
            .title("History")
            .description("Learn how the egyptians build the pyramids")
            .createdAt(LocalDate.now())
            .build();

    return List.of(mathematics, history);
  }

  public List<NoteCollection> getNoteCollections() {
    NoteCollection mathematics =
        NoteCollection.builder()
            .title("Mathematics")
            .description("Learn how to add 1 and 1 together!")
            .build();

    NoteCollection history =
        NoteCollection.builder().title("History").description("Learn about the egyptians").build();

    return List.of(mathematics, history);
  }

  public List<Note> getNotes() {
    Note mathematics =
        Note.builder().title("On the matter of 1 plus 1").body("1 + 1 = 2 not 11!!").build();

    Note history =
        Note.builder().title("On the eye of horus").body("It was a beautiful eye indeed!").build();

    return List.of(mathematics, history);
  }

  public List<Tag> getTags() {
    Tag mathematics = Tag.builder().title("Mathematics").description("Could 2 + 2 be 22?").build();

    Tag history =
        Tag.builder().title("History").description("What happend to the leg of horus?").build();

    return List.of(mathematics, history);
  }

  public List<FlashcardDeck> getFlashcardDecks() {
    FlashcardDeck mathematics =
        FlashcardDeck.builder().title("Mathematics").description("Can you divide 0 by 2?").build();

    FlashcardDeck history =
        FlashcardDeck.builder()
            .title("History")
            .description("The butterfly effect, was if always the same?")
            .build();

    return List.of(mathematics, history);
  }

  public List<Flashcard> getFlashcards() {
    Flashcard mathematics =
        Flashcard.builder()
            .frontBody("Mathematics")
            .backBody("Are modulo and remainder the same?")
            .build();

    Flashcard history =
        Flashcard.builder().frontBody("History").backBody("Who was Quetzalcoatl").build();

    return List.of(mathematics, history);
  }
}
