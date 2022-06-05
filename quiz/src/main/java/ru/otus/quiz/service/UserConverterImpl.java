package ru.otus.quiz.service;

import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.User;

@Service
public class UserConverterImpl implements UserConverter {
  @Override
  public String convertToString(int number, User user) {
    return String.format("%d name: %s %s; score: %d",
      number, user.getFirstName(), user.getLastName(), user.getScore());
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
