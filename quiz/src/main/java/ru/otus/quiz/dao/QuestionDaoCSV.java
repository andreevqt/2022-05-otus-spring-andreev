package ru.otus.quiz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.otus.quiz.config.QuestionsResourceProvider;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;
import ru.otus.quiz.service.QuestionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoCSV implements QuestionDao {

  private final QuestionsResourceProvider resourceProvider;

  @Autowired
  public QuestionDaoCSV(QuestionsResourceProvider resourceProvider) {
    this.resourceProvider = resourceProvider;
  }

  private List<Question> loadData() throws CSVLoadingException {
    try {
      var questions = new ArrayList<Question>();
      var resourcePath = resourceProvider.getQuestionsResource();
      var resource = getClass().getResourceAsStream(resourcePath);
      try (var reader = new BufferedReader(new InputStreamReader(resource))) {
        String line;
        var questionId = 1;
        while ((line = reader.readLine()) != null) {
          var cells = line.split(",");
          var question = new Question(questionId, cells[0], Integer.parseInt(cells[1]));
          for (int i = 2; i < cells.length; i++) {
            var answer = new Answer(cells[i]);
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
