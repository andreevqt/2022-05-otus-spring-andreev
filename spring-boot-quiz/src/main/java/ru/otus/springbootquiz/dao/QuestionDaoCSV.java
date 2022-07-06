package ru.otus.springbootquiz.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.springbootquiz.config.LocaleProvider;
import ru.otus.springbootquiz.config.QuestionsResourceProvider;
import ru.otus.springbootquiz.csv.CSVReader;
import ru.otus.springbootquiz.domain.Answer;
import ru.otus.springbootquiz.domain.Question;
import ru.otus.springbootquiz.exceptions.QuestionsReadingException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Repository
public class QuestionDaoCSV implements QuestionDao {

  private final QuestionsResourceProvider resourceProvider;
  private final LocaleProvider localeProvider;

  @Override
  public List<Question> findAll() {
    return loadData();
  }

  private List<Question> loadData() throws QuestionsReadingException {
    try {
      var questions = new ArrayList<Question>();
      var resourcePath = resourceProvider.getQuestionsResource();
      var resource = getClass().getResourceAsStream("/" + resourcePath);
      var lang = localeProvider.getLocale().getLanguage();
      try (var reader = new CSVReader(new InputStreamReader(resource))) {
        String[] line;
        var questionId = 1;
        while ((line = reader.nextLine()) != null) {
          if (!Objects.equals(line[0], lang)) {
            continue;
          }
          var question = new Question(questionId, line[2]);
          var correctIdx = Integer.parseInt(line[1]);
          for (int i = 3; i < line.length; i++) {
            var correct = i - 2 == correctIdx;
            var answer = new Answer(line[i], correct);
            question.addAnswer(answer);
          }
          questions.add(question);
          questionId++;
        }
      }
      return questions;
    } catch (Exception e) {
      throw new QuestionsReadingException(e);
    }
  }

}
