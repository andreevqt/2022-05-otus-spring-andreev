package ru.otus.quiz.service;

public interface IOService {
  void out(String str);
  int readInt();
  int readIntWithPrompt(String prompt);
}
