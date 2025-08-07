package com.bella.IW2BR;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.EnvironmentRepository;
import com.bella.IW2BR.domain.note.Note;
import com.bella.IW2BR.domain.note.NoteRepository;
import com.bella.IW2BR.domain.notecollection.NoteCollection;
import com.bella.IW2BR.domain.notecollection.NoteCollectionRepository;
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
  private final UserRepository userRepository;
  private final EnvironmentRepository environmentRepository;
  private final NoteCollectionRepository noteCollectionRepository;
  private final NoteRepository noteRepository;
  private final TagRepository tagRepository;
  private final SeederData seederData;

  @Override
  public void run(String... args) throws Exception {
    seedUsers();
    seedEnvironments();
    seedTags();
    seedNoteCollections();
    seedNotes();
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
