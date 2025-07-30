package com.bella.IW2BR.security.refreshtoken;

import com.bella.IW2BR.entities.user.User;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "refresh_token")
@Getter
@Setter
public class RefreshToken {
  @Builder
  public RefreshToken(String token, User user, boolean isRevoked) {
    this.token = token;
    this.user = user;
    this.isRevoked = isRevoked;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "token", nullable = false)
  private String token;

  @OneToOne(optional = false)
  private User user;

  @Column(name = "revoked", nullable = false)
  private boolean isRevoked;
}
