package ru.otus.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.dao.UserDao;
import ru.otus.quiz.domain.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void save(User user) {
    userDao.save(user);
  }

  @Override
  public List<User> listAll() {
    return userDao.findAll();
  }
}
