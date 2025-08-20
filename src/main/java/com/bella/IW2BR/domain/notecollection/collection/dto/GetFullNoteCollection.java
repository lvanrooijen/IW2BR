package com.bella.IW2BR.domain.notecollection.collection.dto;

import com.bella.IW2BR.domain.notecollection.note.dto.GetNote;
import java.util.List;

public record GetFullNoteCollection(
    Long id, String title, String description, List<GetNote> notes) {
  
}
