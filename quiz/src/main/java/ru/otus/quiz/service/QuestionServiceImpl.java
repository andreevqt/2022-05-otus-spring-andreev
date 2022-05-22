package ru.otus.quiz.service;

import ru.otus.quiz.dao.QuestionDao;
import ru.otus.quiz.domain.Question;

import java.util.ArrayList;

public class QuestionServiceImpl implements QuestionService {

  private final QuestionDao questionDao;

  public QuestionServiceImpl(QuestionDao questionDao) {
    this.questionDao = questionDao;
  }

  @Override
  public Question findById(int id) {
    return questionDao.findById(id);
  }

  @Override
  public ArrayList<Question> findAll() {
    return questionDao.findAll();
  }
}
