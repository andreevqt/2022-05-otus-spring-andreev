package ru.otus.springbootquiz.domain;

import lombok.Data;

@Data
public class QuizResult {

  private final Student student;
  private int score;

  public void increment() {
    ++score;
  }

}
