package ru.otus.quiz.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource("classpath:application.properties")
public class AppConfig implements QuestionsResourceProvider {

  private final String questionsResource;

  public AppConfig(@Value("${questions.resource:/questions.csv}") String questionsResource) {
    this.questionsResource = questionsResource;
  }

  @Override
  public String getQuestionsResource() {
    return questionsResource;
  }
}
