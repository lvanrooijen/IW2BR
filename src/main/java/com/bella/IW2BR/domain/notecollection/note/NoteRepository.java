package com.bella.IW2BR.domain.notecollection.note;

import com.bella.IW2BR.domain.notecollection.collection.NoteCollection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  List<Note> findByNotecollection(NoteCollection noteCollection);
}
