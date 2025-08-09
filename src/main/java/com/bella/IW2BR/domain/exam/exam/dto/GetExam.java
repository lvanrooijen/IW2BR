package com.bella.IW2BR.domain.exam.exam.dto;

/**
 * DTO representing how the Exam is returned to the client
 *
 * @param title
 * @param description
 * @param environmentId
 */
public record GetExam(String title, String description, Long environmentId) {}
