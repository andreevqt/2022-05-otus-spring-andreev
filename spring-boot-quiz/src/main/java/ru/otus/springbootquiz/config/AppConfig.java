package ru.otus.springbootquiz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@ConfigurationProperties("application")
@Data
@Component
public class AppConfig implements QuestionsResourceProvider, LocaleProvider {

  private String questionsResource;
  private Locale locale = Locale.getDefault();

  public void setLocale(String locale) {
    this.locale = locale != null ? Locale.forLanguageTag(locale) : Locale.getDefault();
  }
}
