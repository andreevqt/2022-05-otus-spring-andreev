package ru.otus.quiz.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Дао для работы с вопросами")
public class QuestionDaoCSVTest {
  private QuestionDao questionDao;

  @BeforeEach
  void setUp() {
    questionDao = new QuestionDaoCSV("/questions.csv");
  }

  @Test
  void shouldReturnArrayContainingAllQuestions() {
    var questions = questionDao.findAll();
    assertThat(questions).isNotEmpty().isEqualTo(Arrays.asList(
      new Question(
        "I spoke to ____",
        new Answer("she"), new Answer("her")
      ),
      new Question(
        "Where ____ you come from?",
        new Answer("do"), new Answer("are")
      ),
      new Question(
        "What time does she ___ up?",
        new Answer("get"), new Answer("gets")
      ),
      new Question(
        "Where ___ he live?",
        new Answer("do"), new Answer("does")
      ),
      new Question(
        "I am not ____ this film.",
        new Answer("liking"), new Answer("enjoying")
      )
    ));
  }
}
