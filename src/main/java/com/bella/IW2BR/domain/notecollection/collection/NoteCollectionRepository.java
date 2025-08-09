package com.bella.IW2BR.domain.notecollection.collection;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteCollectionRepository extends JpaRepository<NoteCollection, Long> {
  List<NoteCollection> findAllByEnvironmentId(Long environmentId);
}
