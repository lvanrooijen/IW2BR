package com.bella.IW2BR.domain.notecollection.note.dto;

/**
 * DTO representing how the Note is returned to the client
 *
 * @param id
 * @param title
 * @param body
 * @param noteCollectionId
 * @param tagId
 */
public record GetNote(Long id, String title, String body, Long noteCollectionId, Long tagId) {}
