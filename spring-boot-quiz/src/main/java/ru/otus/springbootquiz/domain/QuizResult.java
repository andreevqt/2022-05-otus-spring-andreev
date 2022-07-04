package ru.otus.springbootquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class QuizResult {

  private final Student student;
  private int score;

  public void increment() {
    ++score;
  }

}
