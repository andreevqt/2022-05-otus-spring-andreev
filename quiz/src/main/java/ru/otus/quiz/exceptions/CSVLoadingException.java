package ru.otus.quiz.exceptions;

public class CSVLoadingException extends RuntimeException {
  public CSVLoadingException(Throwable e) {
    super(e);
  }
}
