package ru.otus.quiz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.quiz.service.App;

@ComponentScan
@Configuration
public class Main {
  public static void main(String[] args) {
    try (var context = new AnnotationConfigApplicationContext(Main.class)) {
      var app = context.getBean(App.class);
      app.run();
    }
  }
}
