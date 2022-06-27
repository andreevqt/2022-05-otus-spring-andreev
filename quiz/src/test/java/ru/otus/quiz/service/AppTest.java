package ru.otus.quiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.Question;
import ru.otus.quiz.exceptions.CSVLoadingException;
import ru.otus.quiz.service.converters.Converter;

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
  private Converter<String, QuizResult> quizResultConverter;
  @Mock
  private Converter<String, Question> questionConverter;
  @InjectMocks
  private App app;

  private List<Question> getQuestions() {
    return List.of(new Question(1, "I spoke to ____", 2)
      .addAnswer(new Answer("she"))
      .addAnswer(new Answer("her")));
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
    given(questionService.listAll()).willThrow(CSVLoadingException.class);

    app.run();

    verify(ioService).out("CSV file is corrupt!");
  }

//  @Test
//  void shouldCallOutIfAllGood() {
//    var strToOutput = "John's Doe score is 1";
//    given(questionService.listAll()).willReturn(getQuestions());
//
//    given(ioService.readIntWithPrompt(anyString())).willReturn(2);
//    given(quizResultConverter.convert(any(QuizResult.class))).willReturn(strToOutput);
//
//    app.run();
//
//    verify(ioService).out(strToOutput);
//   }
}
