package ru.otus.springbootquiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.springbootquiz.dao.QuestionDao;
import ru.otus.springbootquiz.domain.Answer;
import ru.otus.springbootquiz.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис для работы с вопросами должен")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class QuestionServiceImplTest {

  @MockBean
  private IOService ioService;
  @MockBean
  private QuestionDao questionDao;

  @Autowired
  private QuestionServiceImpl questionService;

  @BeforeEach
  void setUp() {
    given(questionDao.findAll()).willReturn(List.of(
      new Question(1, "Hello world")
        .addAnswer(new Answer("Yes", true))
        .addAnswer(new Answer("No"))
    ));
  }

  @DisplayName("возвращать список с вопросами")
  @Test
  void shouldReturnListOfQuestions() {
    var questions = questionService.listAll();
    assertThat(questions).isNotEmpty().isEqualTo(List.of(
      new Question(1, "Hello world")
        .addAnswer(new Answer("Yes", true))
        .addAnswer(new Answer("No"))
    ));
  }

}
