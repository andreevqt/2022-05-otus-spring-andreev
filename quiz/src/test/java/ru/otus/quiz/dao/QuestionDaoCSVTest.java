package ru.otus.quiz.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.quiz.config.QuestionsResourceProvider;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;

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
      .willReturn("/questions.csv");
  }

  @Test
  void shouldReturnArrayContainingAllQuestions() {
    var questions = questionDao.findAll();
    assertThat(questions).isNotEmpty().isEqualTo(Arrays.asList(
      new Question(1, "I spoke to ____", 2)
        .addAnswer(new Answer("she"))
        .addAnswer(new Answer("her")),
      new Question(2, "Where ____ you come from?", 2)
        .addAnswer(new Answer("do"))
        .addAnswer(new Answer("are")),
      new Question(3, "What time does she ___ up?", 1)
        .addAnswer(new Answer("get"))
        .addAnswer(new Answer("gets")),
      new Question(4, "Where ___ he live?", 2)
        .addAnswer(new Answer("do"))
        .addAnswer(new Answer("does")),
      new Question(5, "I am not ____ this film.", 2)
        .addAnswer(new Answer("liking"))
        .addAnswer(new Answer("enjoying"))
    ));
  }
}
