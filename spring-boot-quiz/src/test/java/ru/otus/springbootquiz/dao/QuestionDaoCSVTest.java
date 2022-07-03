package ru.otus.springbootquiz.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springbootquiz.config.QuestionsResourceProvider;
import ru.otus.springbootquiz.domain.Answer;
import ru.otus.springbootquiz.domain.Question;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Дао для работы с вопросами")
@ExtendWith(MockitoExtension.class)
public class QuestionDaoCSVTest {

  @Mock
  private QuestionsResourceProvider questionsResourceProvider;

  @InjectMocks
  private QuestionDaoCSV questionDao;

  @BeforeEach
  void setUp() {
    given(questionsResourceProvider.getQuestionsResource())
      .willReturn("questions.csv");
  }

  @DisplayName("Возвращает массив содержащий все вопросы")
  @Test
  void shouldReturnArrayContainingAllQuestions() {
    var questions = questionDao.findAll();
    assertThat(questions).isNotEmpty().isEqualTo(Arrays.asList(
      new Question(1, "I spoke to ____")
        .addAnswer(new Answer("she"))
        .addAnswer(new Answer("her", true)),
      new Question(2, "Where ____ you come from?")
        .addAnswer(new Answer("do"))
        .addAnswer(new Answer("are", true)),
      new Question(3, "What time does she ___ up?")
        .addAnswer(new Answer("get", true))
        .addAnswer(new Answer("gets")),
      new Question(4, "Where ___ he live?")
        .addAnswer(new Answer("do"))
        .addAnswer(new Answer("does", true)),
      new Question(5, "I am not ____ this film.")
        .addAnswer(new Answer("liking"))
        .addAnswer(new Answer("enjoying", true))
    ));
  }
}
