package ru.otus.springbootquiz.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.springbootquiz.config.LocaleProvider;
import ru.otus.springbootquiz.config.QuestionsResourceProvider;
import ru.otus.springbootquiz.domain.Answer;
import ru.otus.springbootquiz.domain.Question;

import java.util.Arrays;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Дао для работы с вопросами должно")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class QuestionDaoCSVTest {

  @MockBean
  private QuestionsResourceProvider questionsResourceProvider;
  @MockBean
  private LocaleProvider localeProvider;
  @Autowired
  private QuestionDaoCSV questionDao;

  @BeforeEach
  void setUp() {
    given(localeProvider.getLocale())
      .willReturn(Locale.ENGLISH);
    given(questionsResourceProvider.getQuestionsResource())
      .willReturn("questions.csv");
  }

  @DisplayName("возвращать список содержащий все вопросы")
  @Test
  void shouldReturnListContainingAllQuestions() {
    var questions = questionDao.findAll();
    assertThat(questions).isNotEmpty().isEqualTo(Arrays.asList(
      new Question(1, "What is the name of the tallest mountain in the world?")
        .addAnswer(new Answer("Mount Everest", true))
        .addAnswer(new Answer("Mount Elbrus")),
      new Question(2, "Which country has the largest population in the world?")
        .addAnswer(new Answer("USA"))
        .addAnswer(new Answer("China", true)),
      new Question(3, "What is the name of the longest river in Africa?")
        .addAnswer(new Answer("The Nile River", true))
        .addAnswer(new Answer("Niger River")),
      new Question(4, "What American city is the Golden Gate Bridge located in?")
        .addAnswer(new Answer("New York"))
        .addAnswer(new Answer("San Francisco", true)),
      new Question(5, "What is the name of the largest country in the world?")
        .addAnswer(new Answer("USA"))
        .addAnswer(new Answer("Russia", true))
    ));
  }

}
