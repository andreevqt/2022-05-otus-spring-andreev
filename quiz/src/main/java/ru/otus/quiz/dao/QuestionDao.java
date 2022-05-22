package ru.otus.quiz.dao;

import ru.otus.quiz.domain.Question;

import java.util.ArrayList;

public interface QuestionDao {
  Question findById(int id);

  Question[] findAll();
}
