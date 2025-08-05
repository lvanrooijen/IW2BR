package com.bella.IW2BR.domain.notecollection;

import com.bella.IW2BR.domain.environment.Environment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "note_collection")
@Getter
@Setter
@NoArgsConstructor
public class NoteCollection {
  @Builder
  public NoteCollection(String title, String description) {
    this.title = title;
    this.description = description;
  }

  @Id @GeneratedValue private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = true)
  private String description;

  @ManyToOne
  @JoinColumn(name = "environment_id", nullable = false)
  private Environment environment;
}
