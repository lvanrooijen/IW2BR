package com.bella.IW2BR;

import com.bella.IW2BR.entities.user.UserRepository;
import com.bella.IW2BR.security.jwt.JwtService;
import com.bella.IW2BR.security.refreshtoken.RefreshTokenRepository;
import com.bella.IW2BR.utils.seederdata.SeederData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtService jwtService;
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
