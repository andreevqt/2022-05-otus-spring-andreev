package ru.otus.quiz.service;

public class IoServiceImpl implements IoService {
  @Override
  public void out(String format, Object... args) {
    System.out.printf(format, args);
  }
}
