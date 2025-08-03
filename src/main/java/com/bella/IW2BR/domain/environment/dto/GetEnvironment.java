package com.bella.IW2BR.domain.environment.dto;

import java.time.LocalDate;

public record GetEnvironment(Long id, String title, String description, LocalDate createdAt) {}
