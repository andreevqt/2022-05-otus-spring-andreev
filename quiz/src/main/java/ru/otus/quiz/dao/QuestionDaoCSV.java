package ru.otus.quiz.dao;

import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


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
          var answers = Arrays.stream(Arrays.copyOfRange(cells, 1, cells.length))
            .map(ans -> new Answer(ans, false))
            .collect(Collectors.toList());
          var question = new Question(text, new ArrayList<>(answers));
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
