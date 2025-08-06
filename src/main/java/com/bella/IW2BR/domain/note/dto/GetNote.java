package com.bella.IW2BR.domain.note.dto;

public record GetNote(Long id, String title, String body, Long noteCollectionId, Long tagId) {}
