package ru.otus.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig implements QuestionsResourceProvider, UsersResourceProvider {

  @Value("${questions.resource:/questions.csv}")
  private String questionsResource;

  @Value("${users.resource:/users.csv}")
  private String usersResource;

  @Override
  public String getQuestionsResource() {
    return questionsResource;
  }

  @Override
  public String getUsersResource() {
    return usersResource;
  }
}
