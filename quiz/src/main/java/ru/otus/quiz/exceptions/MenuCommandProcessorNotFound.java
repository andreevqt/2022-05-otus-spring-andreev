package ru.otus.quiz.exceptions;

public class MenuCommandProcessorNotFound extends RuntimeException {
  public MenuCommandProcessorNotFound(String message) {
    super(message);
  }
}