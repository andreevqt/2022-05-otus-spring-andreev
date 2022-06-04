package ru.otus.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.dao.QuestionDao;
import ru.otus.quiz.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

  private final QuestionDao questionDao;

  @Autowired
  public QuestionServiceImpl(QuestionDao questionDao) {
    this.questionDao = questionDao;
  }

  public List<Question> listAll() {
    return questionDao.findAll();
  }
}
