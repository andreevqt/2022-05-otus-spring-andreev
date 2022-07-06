package ru.otus.springbootquiz.service.converters;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ru.otus.springbootquiz.domain.QuizResult;
import ru.otus.springbootquiz.service.Translator;

@AllArgsConstructor
@Component
public class QuizResultConverterImpl implements QuizResultConverter {

  private final Translator translator;

  @Override
  public String convert(QuizResult source) {
    var student = source.getStudent();
    return translator.translate("quiz.score", student.getFirstName(), student.getLastName(), source.getScore());
  }

}
