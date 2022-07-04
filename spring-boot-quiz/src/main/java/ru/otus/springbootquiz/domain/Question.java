package ru.otus.springbootquiz.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Question {

  private final int id;
  private final String text;
  private final List<Answer> answers = new ArrayList<>();

  public Question addAnswer(Answer answer) {
    answers.add(answer);
    return this;
  }

}
