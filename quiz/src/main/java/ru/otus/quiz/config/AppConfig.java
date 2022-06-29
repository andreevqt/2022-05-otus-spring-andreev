package ru.otus.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig implements QuestionsResourceProvider {

  private final String questionsResource;

  public AppConfig(@Value("${questions.resource}") String questionsResource) {
    this.questionsResource = questionsResource;
  }

  @Override
  public String getQuestionsResource() {
    return questionsResource;
  }
}
