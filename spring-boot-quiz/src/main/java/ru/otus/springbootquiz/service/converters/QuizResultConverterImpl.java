package ru.otus.springbootquiz.service.converters;

import org.springframework.stereotype.Component;
import ru.otus.springbootquiz.domain.QuizResult;

@Component
public class QuizResultConverterImpl implements QuizResultConverter {

  @Override
  public String convert(QuizResult source) {
    var student = source.getStudent();
    return String.format("%s's %s score is %d", student.getFirstName(), student.getLastName(), source.getScore());
  }

}
