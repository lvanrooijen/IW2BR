package com.bella.IW2BR.domain.exam.exam.dto;

/**
 * DTO representing the request body of an Exam PATCH request
 *
 * @param title
 * @param description
 */
public record PostExam(String title, String description) {}
