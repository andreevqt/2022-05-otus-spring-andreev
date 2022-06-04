package ru.otus.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class App {

  private final QuestionService questionService;
  private final IOService ioService;
  private final QuestionConverter questionConverter;

  @Autowired
  public App(QuestionService questionService, IOService ioService,
             QuestionConverter questionConverter) {
    this.questionService = questionService;
    this.ioService = ioService;
    this.questionConverter = questionConverter;
  }

  public void run() {
    for (var q : questionService.listAll()) {
      ioService.out(questionConverter.convertToString(q.getId(), q));
      var answer = ioService.readIntWithPrompt("Your answer: ");
      questionService.answer(q.getId(), answer);
      ioService.out("");
    }

    ioService.out(String.format("Your score is %d", questionService.getScore()));
  }
}
