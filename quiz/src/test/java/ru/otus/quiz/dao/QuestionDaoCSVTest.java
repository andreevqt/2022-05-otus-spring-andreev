package ru.otus.quiz.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.otus.quiz.domain.Question;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Дао для работы с вопросами")
public class QuestionDaoCSVTest {
  private QuestionDao questionDao;

  @BeforeEach
  void setUp() {
    questionDao = new QuestionDaoCSV("questions.csv");
  }

  @ParameterizedTest
  @MethodSource("generateData")
  void shouldReturnNotEmptyQuestionByQuestionId(int id, Question expectedQuestion) {
    var q = questionDao.findById(id);
    assertThat(q).isNotNull().isEqualTo(expectedQuestion);
  }

  @Test
  void shouldReturnArrayContainingAllQuestions() {
    var questions = questionDao.findAll();
    assertThat(questions).isNotEmpty().isEqualTo(new Question[]{
      new Question(1, "I spoke to ____", "she", "her"),
      new Question(2, "Where ____ you come from?", "do", "are"),
      new Question(3, "What time does she ___ up?", "get", "gets"),
      new Question(4, "Where ___ he live?", "do", "does"),
      new Question(5, "I am not ____ this film.", "liking", "enjoying")
    });
  }

  private static Stream<Arguments> generateData() {
    return Stream.of(
      Arguments.of(1, new Question(1, "I spoke to ____", "she", "her")),
      Arguments.of(2, new Question(2, "Where ____ you come from?", "do", "are")),
      Arguments.of(3, new Question(3, "What time does she ___ up?", "get", "gets")),
      Arguments.of(4, new Question(4, "Where ___ he live?", "do", "does")),
      Arguments.of(5, new Question(5, "I am not ____ this film.", "liking", "enjoying"))
    );
  }
}
