package ru.otus.springbootquiz.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {

  private final int id;
  private final String text;
  private final List<Answer> answers = new ArrayList<>();

  public Question addAnswer(Answer answer) {
    answers.add(answer);
    return this;
  }

}
