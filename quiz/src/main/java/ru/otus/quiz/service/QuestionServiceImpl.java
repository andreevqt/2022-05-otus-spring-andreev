package ru.otus.quiz.service;

import ru.otus.quiz.dao.QuestionDao;

public class QuestionServiceImpl implements QuestionService {

  private final QuestionDao questionDao;
  private final IoService ioService;

  public QuestionServiceImpl(QuestionDao questionDao, IoService ioService) {
    this.questionDao = questionDao;
    this.ioService = ioService;
  }

  @Override
  public void listQuestions() {
    var questions = questionDao.findAll();
    for (var question : questions) {
      ioService.out("%s%n", question.getText());
      ioService.out(" • %s%n", question.getAns1());
      ioService.out(" • %s\n%n", question.getAns2());
    }
  }
}
