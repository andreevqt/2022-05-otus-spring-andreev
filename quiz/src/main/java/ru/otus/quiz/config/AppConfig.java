package ru.otus.quiz.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.otus.quiz.service.IOService;
import ru.otus.quiz.service.IOServiceImpl;

@Configuration
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

  @Bean
  public IOService ioService() {
    return new IOServiceImpl(System.in, System.out);
  }
}
