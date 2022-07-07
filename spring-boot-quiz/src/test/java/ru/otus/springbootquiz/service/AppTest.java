package ru.otus.springbootquiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.springbootquiz.domain.Answer;
import ru.otus.springbootquiz.domain.Question;
import ru.otus.springbootquiz.domain.QuizResult;
import ru.otus.springbootquiz.exceptions.QuestionsReadingException;
import ru.otus.springbootquiz.service.converters.QuestionConverter;
import ru.otus.springbootquiz.service.converters.QuizResultConverter;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис для проведения опроса должен")
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class AppTest {

  @MockBean
  private IOService ioService;
  @MockBean
  private QuestionService questionService;
  @MockBean
  private QuizResultConverter quizResultConverter;
  @MockBean
  private QuestionConverter questionConverter;
  @MockBean
  private IOTranslated ioTranslated;
  @Autowired
  private App app;

  private List<Question> getQuestions() {
    return List.of(new Question(1, "I spoke to ____")
      .addAnswer(new Answer("she"))
      .addAnswer(new Answer("her", true)));
  }

  @BeforeEach
  void setUp() {
    given(questionService.listAll()).willReturn(getQuestions());
  }

  @DisplayName("выводить сообщение об ошибке если некорректный индекс ответа")
  @Test
  void shouldOutputErrorIfIndexOutOfBoundsExceptionIfWrongIndex() {
    given(ioTranslated.readIntWithPrompt(anyString())).willReturn(-1);

    app.run();

    verify(ioTranslated).out("exception.index");
  }

  @DisplayName("выводить сообщение об ошибке если не удалось загрузить CSV файл")
  @Test
  void shouldOutputErrorIfFailedToLoadCSVFile() {
    given(questionService.listAll()).willThrow(QuestionsReadingException.class);

    app.run();

    verify(ioTranslated).out("exception.read");
  }

  @DisplayName("выводить результат если все ок")
  @Test
  void shouldCallOutIfAllGood() {
    var msg = "John's Doe score is 1";

    given(ioTranslated.readIntWithPrompt(anyString())).willReturn(2);
    given(quizResultConverter.convert(any(QuizResult.class))).willReturn(msg);

    app.run();

    verify(ioService).out(msg);
  }
}
