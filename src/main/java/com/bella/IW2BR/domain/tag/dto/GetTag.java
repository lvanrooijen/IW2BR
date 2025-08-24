package com.bella.IW2BR.domain.tag.dto;

import com.bella.IW2BR.domain.tag.Tag;

/**
 * DTO representing how the Tag is returned to the client
 *
 * @param id
 * @param title
 * @param description
 * @param score
 * @param environmentId
 */
public record GetTag(Long id, String title, String description, double score, Long environmentId) {
  /**
   * Maps {@link Tag} to {@link GetTag}
   *
   * @param tag {@link Tag}
   * @return {@link GetTag}
   */
  public static GetTag to(Tag tag) {
    return new GetTag(
        tag.getId(),
        tag.getTitle(),
        tag.getDescription(),
        tag.calculateScore(tag),
        tag.getEnvironment().getId());
  }
}
