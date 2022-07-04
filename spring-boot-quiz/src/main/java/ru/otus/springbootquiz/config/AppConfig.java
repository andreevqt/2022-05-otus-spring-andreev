package ru.otus.springbootquiz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@ConfigurationProperties("application")
@Data
@Component
public class AppConfig implements QuestionsResourceProvider, LocaleProvider, QuestionsTranslationProvider {

  private String questionsResource;
  private String questionsTranslationPath;
  private Locale locale = Locale.getDefault();

}
