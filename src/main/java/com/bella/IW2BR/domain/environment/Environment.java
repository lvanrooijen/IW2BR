package com.bella.IW2BR.domain.environment;

import com.bella.IW2BR.domain.flashcarddeck.FlashcardDeck;
import com.bella.IW2BR.domain.notecollection.NoteCollection;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.domain.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.*;

/** Represents environment related to a particular study subject. */
@Entity
@NoArgsConstructor
@Data
@Table(name = "environments")
public class Environment {
  @Builder
  public Environment(String title, String description, LocalDate createdAt, User owner) {
    this.title = title;
    this.description = description;
    this.createdAt = createdAt;
    this.owner = owner;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = true)
  private String description;

  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;

  @ManyToOne
  @JoinColumn(name = "owner", nullable = false)
  private User owner;

  @OneToMany(mappedBy = "environment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FlashcardDeck> flashcardDecks;

  @OneToMany(mappedBy = "environment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<NoteCollection> noteCollections;

  @OneToMany(mappedBy = "environment", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Tag> tags;

  /**
   * Checks if the given ID matches the ID that of the owner
   *
   * @param userId ID of user
   * @return true if ID is from owner
   */
  public boolean isOwner(UUID userId) {
    return owner.getId().equals(userId);
  }
}
