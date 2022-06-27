package ru.otus.quiz.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.Question;
import ru.otus.quiz.exceptions.AnswerIndexOutOfBoundsException;
import ru.otus.quiz.exceptions.CSVLoadingException;

import java.util.List;

@Service
@AllArgsConstructor
public class App {

  private final IOService ioService;
  private final QuestionService questionService;
  private final Converter<String, Question> questionConverter;
  private final Converter<String, QuizResult> quizResultConverter;

  private int readAnswer() {
    return ioService.readIntWithPrompt("Enter your answer:");
  }

  private List<Question> getQuestions() {
    return questionService.listAll();
  }

  private void outputQuestion(Question q) {
    ioService.out(questionConverter.convert(q));
  }

  private QuizResult readCredentials() {
    var firstName = ioService.readStringWithPrompt("Enter your first name");
    var lastName = ioService.readStringWithPrompt("Enter your last name");
    return new QuizResult(firstName, lastName, 0);
  }

  private void outputResult(QuizResult res) {
    ioService.out(quizResultConverter.convert(res));
  }

  private void doQuiz() {
    var result = readCredentials();

    getQuestions().forEach((question -> {
      outputQuestion(question);

      var answerId = readAnswer();
      var answersLen = question.getAnswers().size();
      if (answerId > answersLen || answerId < 1) {
        throw new AnswerIndexOutOfBoundsException("Wrong answer's index");
      }

      if (question.getCorrectAnswer() == answerId) {
        result.increment();
      }
    }));

    outputResult(result);
  }

  public void run() {
    try {
      doQuiz();
    } catch (Throwable e) {
      if (e instanceof AnswerIndexOutOfBoundsException) {
        ioService.out("Wrong Answer's index!");
      } else if (e instanceof CSVLoadingException) {
        ioService.out("CSV file is corrupt!");
      } else {
        ioService.out("Application error! " + e.getMessage());
      }
    }
  }
}
