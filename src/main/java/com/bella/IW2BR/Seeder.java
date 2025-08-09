package com.bella.IW2BR;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentRepository;
import com.bella.IW2BR.domain.exam.exam.Exam;
import com.bella.IW2BR.domain.exam.exam.ExamRepository;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeck;
import com.bella.IW2BR.domain.flashcarddeck.deck.FlashcardDeckRepository;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.Flashcard;
import com.bella.IW2BR.domain.flashcarddeck.flashcard.FlashcardRepository;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.collection.NoteCollectionRepository;
import com.bella.IW2BR.domain.notecollection.note.Note;
import com.bella.IW2BR.domain.notecollection.note.NoteRepository;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.domain.tag.TagRepository;
import com.bella.IW2BR.domain.user.User;
import com.bella.IW2BR.domain.user.UserRepository;
import com.bella.IW2BR.utils.seederdata.SeederData;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
  private final SeederData seederData;
  private final UserRepository userRepository;
  private final EnvironmentRepository environmentRepository;
  private final TagRepository tagRepository;
  private final NoteCollectionRepository noteCollectionRepository;
  private final NoteRepository noteRepository;
  private final FlashcardDeckRepository flashcardDeckRepository;
  private final FlashcardRepository flashcardRepository;
  private final ExamRepository examRepository;

  @Override
  public void run(String... args) throws Exception {
    seedUsers();
    seedEnvironments();
    seedTags();
    seedNoteCollections();
    seedNotes();
    seedFlashcardDecks();
    seedFlashcards();
    seedExams();
  }

  private void seedExams() {
    if (!examRepository.findAll().isEmpty()) return;

    List<Environment> environments = environmentRepository.findAll();
    List<Exam> exams = seederData.getExams();

    exams.get(0).setEnvironment(environments.get(0));
    exams.get(1).setEnvironment(environments.get(1));

    examRepository.saveAll(exams);
  }

  private void seedFlashcards() {
    if (!flashcardRepository.findAll().isEmpty()) return;

    List<FlashcardDeck> flashcardDecks = flashcardDeckRepository.findAll();
    List<Flashcard> flashcards = seederData.getFlashcards();

    flashcards.get(0).setFlashcardDeck(flashcardDecks.get(0));
    flashcards.get(1).setFlashcardDeck(flashcardDecks.get(1));

    flashcardRepository.saveAll(flashcards);
  }

  private void seedFlashcardDecks() {
    if (!flashcardDeckRepository.findAll().isEmpty()) return;

    List<Environment> environments = environmentRepository.findAll();
    List<FlashcardDeck> flashcardDecks = seederData.getFlashcardDecks();

    flashcardDecks.get(0).setEnvironment(environments.get(0));
    flashcardDecks.get(1).setEnvironment(environments.get(1));

    flashcardDeckRepository.saveAll(flashcardDecks);
  }

  private void seedTags() {
    if (!tagRepository.findAll().isEmpty()) return;

    List<Environment> environments = environmentRepository.findAll();
    List<Tag> tags = seederData.getTags();

    tags.get(0).setEnvironment(environments.get(0));
    tags.get(1).setEnvironment(environments.get(1));

    tagRepository.saveAll(tags);
  }

  private void seedNotes() {
    if (!noteRepository.findAll().isEmpty()) return;

    List<NoteCollection> noteCollections = noteCollectionRepository.findAll();
    List<Note> notes = seederData.getNotes();

    notes.get(0).setNotecollection(noteCollections.get(0));
    notes.get(1).setNotecollection(noteCollections.get(1));

    noteRepository.saveAll(notes);
  }

  private void seedNoteCollections() {
    if (!noteCollectionRepository.findAll().isEmpty()) return;

    List<Environment> environments = environmentRepository.findAll();
    List<NoteCollection> noteCollections = seederData.getNoteCollections();

    noteCollections.get(0).setEnvironment(environments.get(0));
    noteCollections.get(1).setEnvironment(environments.get(1));

    noteCollectionRepository.saveAll(noteCollections);
  }

  private void seedEnvironments() {
    if (!environmentRepository.findAll().isEmpty()) return;

    List<User> users = userRepository.findAll();

    List<Environment> environments = seederData.getEnvironments();
    environments.forEach(environment -> environment.setOwner(users.get(0)));

    environmentRepository.saveAll(environments);
  }

  private void seedUsers() {
    if (!userRepository.findAll().isEmpty()) return;

    userRepository.saveAll(seederData.getUsers());
  }
}
