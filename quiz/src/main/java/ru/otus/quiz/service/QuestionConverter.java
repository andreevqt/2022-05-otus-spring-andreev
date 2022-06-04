package ru.otus.quiz.service;

import ru.otus.quiz.domain.Question;

public interface QuestionConverter {
  String convertToString(int number, Question q);
}
