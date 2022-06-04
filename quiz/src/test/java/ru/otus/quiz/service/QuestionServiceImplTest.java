package ru.otus.quiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.quiz.dao.QuestionDao;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис для работы с вопросами")
@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest {

  @Mock
  private IOService ioService;

  @Mock
  private QuestionDao questionDao;

  @InjectMocks
  private QuestionServiceImpl questionService;

  @BeforeEach
  void setUp() {
    given(questionDao.findAll()).willReturn(new ArrayList<>(List.of(
      new Question(1, "Hello world", 1)
        .addAnswer(new Answer("Yes"))
        .addAnswer(new Answer("No"))
    )));
  }

  @Test
  void shouldExecuteServiceMethodsIfListQuestionsCalled() {
    questionService.listQuestions();
    verify(questionDao, times(1)).findAll();
    verify(ioService, times(1)).out("%s%n", "Hello world");
    verify(ioService, times(1)).out("%o) %s%n", 1, "Yes");
    verify(ioService, times(1)).out("%o) %s%n", 2, "No");
    verify(ioService, times(1)).out("%n");
  }
}
