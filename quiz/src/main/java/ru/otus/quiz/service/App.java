package ru.otus.quiz.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.Answer;
import ru.otus.quiz.domain.QuizResult;
import ru.otus.quiz.domain.Student;
import ru.otus.quiz.exceptions.AnswerIndexOutOfBoundsException;
import ru.otus.quiz.exceptions.QuestionsReadingException;
import ru.otus.quiz.service.converters.QuestionConverter;
import ru.otus.quiz.service.converters.QuizResultConverter;

import java.util.List;

@Service
@AllArgsConstructor
public class App {

  private final IOService ioService;
  private final QuestionService questionService;
  private final QuestionConverter questionConverter;
  private final QuizResultConverter quizResultConverter;

  public void run() {
    try {
      doQuiz();
    } catch (Exception e) {
      if (e instanceof AnswerIndexOutOfBoundsException) {
        ioService.out("Wrong Answer's index!");
      } else if (e instanceof QuestionsReadingException) {
        ioService.out("Failed to read questions!");
      } else {
        ioService.out("Application error! " + e.getMessage());
      }
    }
  }

  private boolean isAnswerOutOfBounds(int answerIdx, List<Answer> answers) {
    return answerIdx > answers.size() || answerIdx < 1;
  }

  private Student readStudent() {
    var firstName = ioService.readStringWithPrompt("Enter your first name");
    var lastName = ioService.readStringWithPrompt("Enter your last name");
    return new Student(firstName, lastName);
  }

  private void doQuiz() {
    var student = readStudent();
    var result = new QuizResult(student, 0);

    questionService.listAll().forEach((question -> {
      ioService.out(questionConverter.convert(question));

      var answerIdx = ioService.readIntWithPrompt("Enter your answer:");
      var answers = question.getAnswers();
      if (isAnswerOutOfBounds(answerIdx, answers)) {
        throw new AnswerIndexOutOfBoundsException("Wrong answer's index");
      }

      var answer = answers.get(answerIdx - 1);
      if (answer.isCorrect()) {
        result.increment();
      }
    }));

    ioService.out(quizResultConverter.convert(result));
  }
}
