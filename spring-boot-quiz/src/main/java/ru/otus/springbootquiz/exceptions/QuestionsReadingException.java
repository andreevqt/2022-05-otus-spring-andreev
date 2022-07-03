package ru.otus.springbootquiz.exceptions;

public class QuestionsReadingException extends RuntimeException {

  public QuestionsReadingException(String message) {
    super(message);
  }
  
  public QuestionsReadingException(Exception cause) {
    super(cause);
  }

}
