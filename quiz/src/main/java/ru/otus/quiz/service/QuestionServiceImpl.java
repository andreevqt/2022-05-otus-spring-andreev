package ru.otus.quiz.service;

import ru.otus.quiz.dao.QuestionDao;

public class QuestionServiceImpl implements QuestionService {

  private final QuestionDao questionDao;
  private final IOService ioService;

  public QuestionServiceImpl(QuestionDao questionDao, IOService ioService) {
    this.questionDao = questionDao;
    this.ioService = ioService;
  }

  @Override
  public void listQuestions() {
    var questions = questionDao.findAll();
    for (var question : questions) {
      ioService.out("%s%n", question.getText());
      var answers = question.getAnswers();
      for (int i = 0; i < answers.size(); i++) {
        var answer = answers.get(i);
        ioService.out("%o) %s%n", i + 1, answer.getText());
      }
      ioService.out("%n");
    }
  }
}
