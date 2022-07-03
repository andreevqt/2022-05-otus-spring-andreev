package ru.otus.springbootquiz.service.converters;

import ru.otus.springbootquiz.domain.Question;

public interface QuestionConverter {

  String convert(Question q);

}
