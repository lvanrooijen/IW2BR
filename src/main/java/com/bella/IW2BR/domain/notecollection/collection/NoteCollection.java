package com.bella.IW2BR.domain.notecollection.collection;

import com.bella.IW2BR.domain.environment.Environment;
import com.bella.IW2BR.domain.environment.util.EnvironmentMember;
import com.bella.IW2BR.domain.notecollection.note.Note;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "note_collection")
@Data
@NoArgsConstructor
public class NoteCollection implements EnvironmentMember {
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

  @OneToMany(mappedBy = "notecollection", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Note> notes;
}
