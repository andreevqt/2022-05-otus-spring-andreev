package ru.otus.quiz.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class QuizResult {
  private final String firstName;
  private final String lastName;
  private int score;

  public void increment() {
    ++score;
  }
}
