package ru.otus.quiz.service;

import ru.otus.quiz.domain.User;

import java.util.List;

public interface UserService {
  void save(User user);

  List<User> listAll();
}
