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

@DisplayName("Сервис для проведения опроса должен")
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
  @Mock
  private Translator translator;
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

  @DisplayName("возвращать ошибку если некорректный индекс ответа")
  @Test
  void shouldOutputErrorIfIndexOutOfBoundsExceptionIfWrongIndex() {
    var msg = "Error! Wrong Answer's index!";

    given(ioService.readIntWithPrompt(anyString())).willReturn(-1);
    given(translator.translate("exception.index")).willReturn(msg);

    app.run();

    verify(ioService).out(msg);
  }

  @DisplayName("выводить сообщение об ошибке если не удалось загрузаить CSV файл")
  @Test
  void shouldOutputErrorIfFailedToLoadCSVFile() {
    var msg = "Error! Failed to read questions!";

    given(questionService.listAll()).willThrow(QuestionsReadingException.class);
    given(translator.translate("exception.read")).willReturn(msg);

    app.run();

    verify(ioService).out(msg);
  }

  @DisplayName("выводить результат если все ок")
  @Test
  void shouldCallOutIfAllGood() {
    var msg = "John's Doe score is 1";

    given(ioService.readIntWithPrompt(anyString())).willReturn(2);
    given(quizResultConverter.convert(any(QuizResult.class))).willReturn(msg);

    app.run();

    verify(ioService).out(msg);
  }
}
