package ru.otus.quiz.service.converters;

import org.springframework.stereotype.Component;
import ru.otus.quiz.service.QuizResult;

@Component
public class QuizResultConverter implements Converter<String, QuizResult> {
  @Override
  public String convert(QuizResult source) {
    return String.format("%s's %s score is %d", source.getFirstName(), source.getLastName(), source.getScore());
  }
}
