package com.bella.IW2BR.domain.exam.exam.dto;

/**
 * DTO representing how the Exam is returned to the client
 *
 * @param title
 * @param description
 */
public record GetExam(Long id, String title, String description) {}
