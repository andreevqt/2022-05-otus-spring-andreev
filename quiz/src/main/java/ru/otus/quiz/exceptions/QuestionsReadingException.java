package ru.otus.quiz.exceptions;

public class QuestionsReadingException extends RuntimeException {

  public QuestionsReadingException(String message) {
    super(message);
  }
  
  public QuestionsReadingException(Exception cause) {
    super(cause);
  }

}
