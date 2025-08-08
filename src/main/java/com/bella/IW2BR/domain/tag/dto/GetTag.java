package com.bella.IW2BR.domain.tag.dto;

/**
 * DTO representing how the Tag is returned to the client
 *
 * @param id
 * @param title
 * @param description
 * @param score
 * @param environmentId
 */
public record GetTag(Long id, String title, String description, double score, Long environmentId) {}
