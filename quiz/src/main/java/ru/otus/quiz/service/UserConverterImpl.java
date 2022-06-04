package ru.otus.quiz.service;

import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.User;

@Service
public class UserConverterImpl implements UserConverter {
  @Override
  public String convertToString(User user) {
    return user.getFirstName() + " " + user.getLastName();
  }

  @Override
  public String[] convertToArray(User user) {
    return new String[]{
      user.getFirstName(),
      user.getLastName(),
      String.valueOf(user.getScore())
    };
  }
}
