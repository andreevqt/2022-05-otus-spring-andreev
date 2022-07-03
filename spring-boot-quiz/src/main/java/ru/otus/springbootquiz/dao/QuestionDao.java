package ru.otus.springbootquiz.dao;

import ru.otus.springbootquiz.domain.Question;

import java.util.List;

public interface QuestionDao {

  List<Question> findAll();

}
