package ru.otus.springbootquiz.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import ru.otus.springbootquiz.config.LocaleProvider;
import ru.otus.springbootquiz.config.QuestionsResourceProvider;
import ru.otus.springbootquiz.config.QuestionsTranslationProvider;
import ru.otus.springbootquiz.csv.CSVReader;
import ru.otus.springbootquiz.domain.Answer;
import ru.otus.springbootquiz.domain.Question;
import ru.otus.springbootquiz.exceptions.QuestionsReadingException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class QuestionDaoCSV implements QuestionDao {

  private final QuestionsResourceProvider resourceProvider;
  private final QuestionsTranslationProvider translationProvider;
  private final LocaleProvider localeProvider;

  @Override
  public List<Question> findAll() {
    return loadData();
  }

  private Question loadQuestion(int id, int correctIndex) throws QuestionsReadingException {
    try {
      var locale = localeProvider.getLocale();
      var translationsPath = String.format("%s/%s.csv", translationProvider.getQuestionsTranslationPath(),
          locale.getLanguage());
      var resource = getClass().getResourceAsStream("/" + translationsPath);
      try (var reader = new CSVReader(new InputStreamReader(resource))) {
        String[] line;
        int lineNum = 1;
        while ((line = reader.nextLine()) != null) {
          if (id == lineNum) {
            var question = new Question(id, line[0]);
            for (int i = 1; i < line.length; i++) {
              var correct = i == correctIndex;
              var answer = new Answer(line[i], correct);
              question.addAnswer(answer);
            }
            return question;
          }
          lineNum++;
        }
        return null;
      }
    } catch (Exception e) {
      throw new QuestionsReadingException(e);
    }
  }

  private List<Question> loadData() throws QuestionsReadingException {
    try {
      var questions = new ArrayList<Question>();
      var resourcePath = resourceProvider.getQuestionsResource();
      var resource = getClass().getResourceAsStream("/" + resourcePath);
      try (var reader = new CSVReader(new InputStreamReader(resource))) {
        String[] line;
        var questionId = 1;
        while ((line = reader.nextLine()) != null) {
          var correctIdx = Integer.parseInt(line[0]);
          var question = loadQuestion(questionId, correctIdx);
          if (question == null) {
            throw new QuestionsReadingException(String.format("Couldn't find translation for question %d", questionId));
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
