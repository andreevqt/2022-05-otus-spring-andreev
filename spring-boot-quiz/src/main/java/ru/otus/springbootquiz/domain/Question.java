package ru.otus.springbootquiz.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Question {

  private final int id;
  private final String text;
  private final List<Answer> answers = new ArrayList<>();

  public Question addAnswer(Answer answer) {
    answers.add(answer);
    return this;
  }

}
