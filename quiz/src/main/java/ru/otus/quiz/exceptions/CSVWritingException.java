package ru.otus.quiz.exceptions;

public class CSVWritingException extends RuntimeException {
  public CSVWritingException(Throwable e) {
    super(e);
  }
}

