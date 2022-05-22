package ru.otus.quiz.service;

import ru.otus.quiz.dao.QuestionDao;

public class QuestionServiceImpl implements QuestionService {

  private final QuestionDao questionDao;

  public QuestionServiceImpl(QuestionDao questionDao) {
    this.questionDao = questionDao;
  }

  @Override
  public void listQuestions() {
    var questions = questionDao.findAll();
    for (var question : questions) {
      System.out.printf("%s%n", question.getText());
      System.out.printf(" • %s%n", question.getAns1());
      System.out.printf(" • %s\n%n", question.getAns2());
    }
  }
}
