package com.bella.IW2BR.domain.exam.exam.util;

/**
 * Enum that represents the amount of answers a question can have
 *
 * <p>Options:
 *
 * <ul>
 *   <li>SMALL ----> 2 answers
 *   <li>MEDIUM --> 4 answers
 *   <li>LARGE ----> 6 answers
 * </ul>
 */
public enum QuestionSize {
  SMALL(2),
  MEDIUM(4),
  LARGE(6);

  private final int amount;

  QuestionSize(int amount) {
    this.amount = amount;
  }

  public int getAnswerAmount() {
    return this.amount;
  }
}
