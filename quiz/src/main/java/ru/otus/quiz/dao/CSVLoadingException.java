package ru.otus.quiz.dao;

public class CSVLoadingException extends RuntimeException {
  public CSVLoadingException(Throwable e) {
    super(e);
  }
}
