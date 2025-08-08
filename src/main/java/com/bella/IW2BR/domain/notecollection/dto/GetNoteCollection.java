package com.bella.IW2BR.domain.notecollection.dto;

/**
 * DTO representing how the Note-collection is returned to the client
 *
 * @param id
 * @param title
 * @param description
 */
public record GetNoteCollection(Long id, String title, String description) {}
