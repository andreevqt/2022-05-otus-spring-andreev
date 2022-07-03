package ru.otus.springbootquiz.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Answer {

  private final String text;
  private boolean correct;

  public Answer(String text) {
    this(text, false);
  }

}
