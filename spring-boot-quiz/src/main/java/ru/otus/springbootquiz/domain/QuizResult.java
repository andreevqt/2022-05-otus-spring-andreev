package ru.otus.springbootquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuizResult {

  private final Student student;
  private int score;

  public void increment() {
    ++score;
  }

}
