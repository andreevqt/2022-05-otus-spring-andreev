package ru.otus.springbootquiz.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AnswerIndexOutOfBoundsException extends IndexOutOfBoundsException {

  public AnswerIndexOutOfBoundsException(String s) {
    super(s);
  }

}
