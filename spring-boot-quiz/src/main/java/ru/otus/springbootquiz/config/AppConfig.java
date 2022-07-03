package ru.otus.springbootquiz.config;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("application")
@Setter
@Component
public class AppConfig implements QuestionsResourceProvider {

  private String questionsResource;

  @Override
  public String getQuestionsResource() {
    return questionsResource;
  }
  
}
