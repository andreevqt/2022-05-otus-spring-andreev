package ru.otus.springbootquiz.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Answer {

  private final String text;
  private boolean correct;

  public Answer(String text) {
    this(text, false);
  }

}
