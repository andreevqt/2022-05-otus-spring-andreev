package ru.otus.quiz.service.converters;

import ru.otus.quiz.domain.Question;

public interface QuestionConverter {
  String convert(Question q);
}
