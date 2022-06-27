package ru.otus.quiz.service;

import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.Question;

@Service
public class QuestionConverter implements Converter<String, Question> {

  public String convert(Question q) {
    var sb = new StringBuilder(String.format("%d. %s:\n", q.getId(), q.getText()));
    var answers = q.getAnswers();
    for (int i = 0; i < answers.size(); i++) {
      var answer = answers.get(i);
      sb.append(String.format("\t%d) %s", i + 1, answer.getText()));
      if (i < answers.size() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }
}
