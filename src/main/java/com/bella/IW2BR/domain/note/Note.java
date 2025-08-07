package com.bella.IW2BR.domain.note;

import com.bella.IW2BR.domain.notecollection.NoteCollection;
import com.bella.IW2BR.domain.tag.Tag;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "notes")
@NoArgsConstructor
public class Note {
  @Builder
  public Note(String title, String body, NoteCollection notecollection, Tag tag) {
    this.title = title;
    this.body = body;
    this.notecollection = notecollection;
    this.tag = tag;
  }

  @Id @GeneratedValue private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "body", nullable = false)
  private String body;

  @ManyToOne
  @JoinColumn(name = "note_collection_id")
  private NoteCollection notecollection;

  @ManyToOne
  @JoinColumn(name = "tag_id")
  private Tag tag;
}
