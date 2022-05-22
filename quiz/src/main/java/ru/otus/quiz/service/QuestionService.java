package ru.otus.quiz.service;

import ru.otus.quiz.domain.Question;

import java.util.ArrayList;

public interface QuestionService {
  Question findById(int id);

  ArrayList<Question> findAll();
}
