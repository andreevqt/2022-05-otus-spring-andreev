package ru.otus.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig implements QuestionsResourceProvider{

  @Value("${questions.resource:/questions.csv}")
  private String questionsResource;

  @Override
  public String getQuestionsResource() {
    return questionsResource;
  }
}
