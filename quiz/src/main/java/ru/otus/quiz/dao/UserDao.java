package ru.otus.quiz.dao;

import ru.otus.quiz.domain.User;

import java.util.List;

public interface UserDao {
  List<User> findAll();

  void save(User u);
}
