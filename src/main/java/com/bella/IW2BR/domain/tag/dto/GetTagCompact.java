package com.bella.IW2BR.domain.tag.dto;

import com.bella.IW2BR.domain.tag.Tag;

/**
 * DTO representing how tag is returned to the client.
 *
 * <p>contains minimal information
 *
 * @param id
 * @param title
 */
public record GetTagCompact(Long id, String title) {
  public static GetTagCompact to(Tag tag) {
    return new GetTagCompact(tag.getId(), tag.getTitle());
  }
}
