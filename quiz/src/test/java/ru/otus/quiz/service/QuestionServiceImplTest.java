package ru.otus.quiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.quiz.dao.QuestionDao;
import ru.otus.quiz.domain.Question;

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

  private QuestionService questionService;

  @BeforeEach
  void setUp() {
    given(questionDao.findAll()).willReturn(new Question[]{
      new Question(1, "Hello world", "Yes", "No")
    });
    questionService = new QuestionServiceImpl(questionDao, ioService);
  }

  @Test
  void shouldExecuteServiceMethodsIfListQuestionsCalled() {
    questionService.listQuestions();
    verify(questionDao, times(1)).findAll();
    verify(ioService, times(1)).out("%s%n", "Hello world");
    verify(ioService, times(1)).out("• %s%n", "Yes");
    verify(ioService, times(1)).out("• %s\n%n", "No");
  }
}
