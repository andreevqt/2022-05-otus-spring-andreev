package ru.otus.springbootquiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
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

@DisplayName("Сервис для проведения опроса")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AppTest {

  @Mock
  private IOService ioService;
  @Mock
  private QuestionService questionService;
  @Mock
  private QuizResultConverter quizResultConverter;
  @Mock
  private QuestionConverter questionConverter;
  @InjectMocks
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

  @Test
  void shouldOutputErrorIfIndexOutOfBoundsExceptionIfWrongIndex() {
    given(ioService.readIntWithPrompt(anyString())).willReturn(-1);

    app.run();

    verify(ioService).out("Wrong Answer's index!");
  }

  @Test
  void shouldOutputErrorIfFailedToLoadCSVFile() {
    given(questionService.listAll()).willThrow(QuestionsReadingException.class);

    app.run();

    verify(ioService).out("Failed to read questions!");
  }

  @Test
  void shouldCallOutIfAllGood() {
    var strToOutput = "John's Doe score is 1";
    given(questionService.listAll()).willReturn(getQuestions());

    given(ioService.readIntWithPrompt(anyString())).willReturn(2);
    given(quizResultConverter.convert(any(QuizResult.class))).willReturn(strToOutput);

    app.run();

    verify(ioService).out(strToOutput);
  }
}
