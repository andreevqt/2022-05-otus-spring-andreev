package ru.otus.quiz;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.quiz.service.QuestionService;

public class Main {
  public static void main(String[] args) {
    var context = new ClassPathXmlApplicationContext("/spring-context.xml");
    var questionService = context.getBean(QuestionService.class);
    questionService.listQuestions();
    context.close();
  }
}
