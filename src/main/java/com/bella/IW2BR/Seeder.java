package com.bella.IW2BR;

import com.bella.IW2BR.entities.user.UserRepository;
import com.bella.IW2BR.utils.seederdata.SeederData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
  private final UserRepository userRepository;
  private final SeederData seederData;

  @Override
  public void run(String... args) throws Exception {
    seedUsers();
  }

  private void seedUsers() {
    if (!userRepository.findAll().isEmpty()) return;

    userRepository.saveAll(seederData.getUsers());
  }
}
