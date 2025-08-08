package com.bella.IW2BR.domain.environment.dto;

import java.time.LocalDate;

/**
 * DTO representing how the Environment is returned to the client
 *
 * @param id
 * @param title
 * @param description
 * @param createdAt Date the Environment was created
 */
public record GetEnvironment(Long id, String title, String description, LocalDate createdAt) {}
