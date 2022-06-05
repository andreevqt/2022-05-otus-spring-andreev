package ru.otus.quiz.service;

import ru.otus.quiz.domain.User;

public interface UserConverter {
  String convertToString(int number, User user);

  String[] convertToArray(User user);
}
