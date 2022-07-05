package ru.otus.springbootquiz.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springbootquiz.config.LocaleProvider;
import ru.otus.springbootquiz.config.QuestionsResourceProvider;
import ru.otus.springbootquiz.domain.Answer;
import ru.otus.springbootquiz.domain.Question;

import java.util.Arrays;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Дао для работы с вопросами должно")
@ExtendWith(MockitoExtension.class)
public class QuestionDaoCSVTest {

  @Mock
  private QuestionsResourceProvider questionsResourceProvider;
  @Mock
  private LocaleProvider localeProvider;
  @InjectMocks
  private QuestionDaoCSV questionDao;

  @BeforeEach
  void setUp() {
    given(localeProvider.getLocale())
      .willReturn(Locale.ENGLISH);
    given(questionsResourceProvider.getQuestionsResource())
      .willReturn("questions.csv");
  }

  @DisplayName("возвращать массив содержащий все вопросы")
  @Test
  void shouldReturnArrayContainingAllQuestions() {
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
