package ru.otus.quiz;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.quiz.service.QuestionService;

public class Main {
  public static void main(String[] args) {
    var context = new ClassPathXmlApplicationContext("/spring-context.xml");
    var questionService = context.getBean(QuestionService.class);
    var questions = questionService.findAll();
    for (var question : questions) {
      System.out.printf("%s%n", question.getText());
      System.out.printf(" • %s%n", question.getAns1());
      System.out.printf(" • %s\n%n", question.getAns2());
    }
    context.close();
  }
}
