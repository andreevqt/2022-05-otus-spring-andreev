package ru.otus.springbootquiz.service;

import ru.otus.springbootquiz.domain.Question;

import java.util.List;

public interface QuestionService {

  List<Question> listAll();

}
