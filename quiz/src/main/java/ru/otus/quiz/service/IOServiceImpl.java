package ru.otus.quiz.service;

public class IOServiceImpl implements IOService {
  @Override
  public void out(String format, Object... args) {
    System.out.printf(format, args);
  }
}
