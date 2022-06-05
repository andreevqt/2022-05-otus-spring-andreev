package ru.otus.quiz.service;

import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.User;

import java.util.Comparator;

@Service
public class UserComparatorImpl implements Comparator<User> {
  @Override
  public int compare(User o1, User o2) {
    return o1.getScore() - o2.getScore();
  }
}
