package com.bella.IW2BR.entities.environment.dto;

import java.time.LocalDate;

public record GetEnvironment(Long id, String title, String description, LocalDate createdAt) {}
