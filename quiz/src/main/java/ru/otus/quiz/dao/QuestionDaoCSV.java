package ru.otus.quiz.dao;

import ru.otus.quiz.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class QuestionDaoCSV implements QuestionDao {

  private final Map<Integer, Question> questions;
  private final String resourceName;

  public QuestionDaoCSV(String resourceName) {
    this.resourceName = resourceName;
    this.questions = new HashMap<>();
    loadData();
  }

  private void loadData() throws CSVLoadingException {
    try {
      var resource = getClass().getResourceAsStream("/" + resourceName);
      try (var reader = new BufferedReader(new InputStreamReader(resource))) {
        String line;
        while ((line = reader.readLine()) != null) {
          var cells = line.split(",");
          var id = Integer.parseInt(cells[0]);
          var question = new Question(id, cells[1], cells[2], cells[3]);
          questions.put(id, question);
        }
      }
    } catch (IOException e) {
      throw new CSVLoadingException(e);
    }
  }

  @Override
  public Question findById(int id) {
    return questions.get(id);
  }

  @Override
  public Question[] findAll() {
    return questions.values().toArray(new Question[0]);
  }
}
