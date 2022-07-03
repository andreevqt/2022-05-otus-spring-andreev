package ru.otus.springbootquiz.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.springbootquiz.config.QuestionsResourceProvider;
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

  @Override
  public List<Question> findAll() {
    return loadData();
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
          var question = new Question(questionId, line[0]);
          var correctIdx = Integer.parseInt(line[1]);
          for (int i = 2; i < line.length; i++) {
            var correct = i - 1 == correctIdx;
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
