package com.bella.IW2BR.utils.seederdata;

import com.bella.IW2BR.domain.user.Role;
import com.bella.IW2BR.domain.user.User;
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
}
