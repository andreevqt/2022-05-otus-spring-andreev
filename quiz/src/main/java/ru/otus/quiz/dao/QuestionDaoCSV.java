package ru.otus.quiz.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.quiz.config.QuestionsResourceProvider;
import ru.otus.quiz.csv.CSVReader;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;
import ru.otus.quiz.exceptions.CSVLoadingException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class QuestionDaoCSV implements QuestionDao {

  private final QuestionsResourceProvider resourceProvider;

  private List<Question> loadData() throws CSVLoadingException {
    try {
      var questions = new ArrayList<Question>();
      var resourcePath = resourceProvider.getQuestionsResource();
      var resource = getClass().getResourceAsStream(resourcePath);
      try (var reader = new CSVReader(new InputStreamReader(resource))) {
        String[] line;
        var questionId = 1;
        while ((line = reader.nextLine()) != null) {
          var question = new Question(questionId, line[0], Integer.parseInt(line[1]));
          for (int i = 2; i < line.length; i++) {
            var answer = new Answer(line[i]);
            question.addAnswer(answer);
          }
          questions.add(question);
          questionId++;
        }
      }
      return questions;
    } catch (IOException e) {
      throw new CSVLoadingException(e);
    }
  }

  @Override
  public List<Question> findAll() {
    return loadData();
  }
}
