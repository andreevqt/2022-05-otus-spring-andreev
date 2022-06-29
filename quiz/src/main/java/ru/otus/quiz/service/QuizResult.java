package ru.otus.quiz.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.quiz.domain.Student;

@AllArgsConstructor
@Getter
public class QuizResult {

  private final Student student;
  private int score;

  public void increment() {
    ++score;
  }

}
