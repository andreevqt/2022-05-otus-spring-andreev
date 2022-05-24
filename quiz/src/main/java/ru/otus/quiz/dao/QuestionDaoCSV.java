package ru.otus.quiz.dao;

import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuestionDaoCSV implements QuestionDao {

  private final String resourceName;

  public QuestionDaoCSV(String resourceName) {
    this.resourceName = resourceName;
  }

  private ArrayList<Question> loadData() throws CSVLoadingException {
    try {
      var questions = new ArrayList<Question>();
      var resource = getClass().getResourceAsStream(resourceName);
      try (var reader = new BufferedReader(new InputStreamReader(resource))) {
        String line;
        while ((line = reader.readLine()) != null) {
          var cells = line.split(",");
          var text = cells[0];
          var question = new Question(text);
          for (int i = 1; i < cells.length; i++) {
            var answer = new Answer(cells[i]);
            question.addAnswer(answer);
          }
          questions.add(question);
        }
      }
      return questions;
    } catch (IOException e) {
      throw new CSVLoadingException(e);
    }
  }

  @Override
  public ArrayList<Question> findAll() {
    return loadData();
  }
}
