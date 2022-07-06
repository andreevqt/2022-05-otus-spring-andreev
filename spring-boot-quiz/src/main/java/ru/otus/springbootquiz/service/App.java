package ru.otus.springbootquiz.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springbootquiz.domain.Answer;
import ru.otus.springbootquiz.domain.QuizResult;
import ru.otus.springbootquiz.domain.Student;
import ru.otus.springbootquiz.exceptions.AnswerIndexOutOfBoundsException;
import ru.otus.springbootquiz.exceptions.QuestionsReadingException;
import ru.otus.springbootquiz.service.converters.QuestionConverter;
import ru.otus.springbootquiz.service.converters.QuizResultConverter;

import java.util.List;

@Service
@AllArgsConstructor
public class App {

  private final IOService ioService;
  private final QuestionService questionService;
  private final QuestionConverter questionConverter;
  private final QuizResultConverter quizResultConverter;
  private final IOTranslated ioTranslated;

  public void run() {
    try {
      doQuiz();
    } catch (Exception e) {
      if (e instanceof AnswerIndexOutOfBoundsException) {
        ioTranslated.out("exception.index");
      } else if (e instanceof QuestionsReadingException) {
        ioTranslated.out("exception.read");
      } else {
        ioTranslated.out("exception.error", e.getMessage());
      }
    }
  }

  private boolean isAnswerOutOfBounds(int answerIdx, List<Answer> answers) {
    return answerIdx > answers.size() || answerIdx < 1;
  }

  private Student readStudent() {
    var firstName = ioTranslated.readStringWithPrompt("quiz.first_name");
    var lastName = ioTranslated.readStringWithPrompt("quiz.last_name");
    return new Student(firstName, lastName);
  }

  private void doQuiz() {
    var student = readStudent();
    var result = new QuizResult(student);

    questionService.listAll().forEach((question -> {
      ioService.out(questionConverter.convert(question));

      var answerIdx = ioTranslated.readIntWithPrompt("quiz.answer");
      var answers = question.getAnswers();
      if (isAnswerOutOfBounds(answerIdx, answers)) {
        throw new AnswerIndexOutOfBoundsException();
      }

      var answer = answers.get(answerIdx - 1);
      if (answer.isCorrect()) {
        result.increment();
      }
    }));

    ioService.out(quizResultConverter.convert(result));
  }
}
