package ru.otus.quiz.service.processors;

import ru.otus.quiz.domain.Question;
import ru.otus.quiz.domain.User;
import ru.otus.quiz.exceptions.AnswerIndexOutOfBoundsException;
import ru.otus.quiz.service.IOService;
import ru.otus.quiz.service.QuestionConverter;
import ru.otus.quiz.service.QuestionService;
import ru.otus.quiz.service.UserService;
import ru.otus.quiz.service.menu.MenuOption;

import java.util.HashMap;
import java.util.Map;

public class StartQuizCommandProcessor implements MenuSingleCommandProcessor {

  private final Map<Integer, Boolean> answers;
  private final QuestionService questionService;
  private final MenuOption processedCommandOption;
  private final QuestionConverter questionConverter;
  private final IOService ioService;
  private final UserService userService;

  public StartQuizCommandProcessor(QuestionService questionService,
                                   MenuOption processedCommandOption, QuestionConverter questionConverter,
                                   IOService ioService, UserService userService) {
    this.questionService = questionService;
    this.processedCommandOption = processedCommandOption;
    this.questionConverter = questionConverter;
    this.ioService = ioService;
    this.userService = userService;
    this.answers = new HashMap<>();
  }

  private void doAnswer(int questionId, int answer) {
    var questions = questionService.listAll();
    questions.stream()
      .filter(q -> q.getId() == questionId)
      .findFirst()
      .ifPresent(question -> answers.put(
        questionId,
        question.getCorrectAnswer() == answer
      ));
  }

  private int getScore() {
    return answers.values().stream().reduce(0, (acc, value) -> acc + (value ? 1 : 0), Integer::sum);
  }

  private User readUser() {
    // consume new line
    ioService.readStringWithPrompt("");

    var firstName = ioService.readStringWithPrompt("Enter your first name:");
    var lastName = ioService.readStringWithPrompt("Enter your last name:");
    ioService.out("");

    return new User(firstName, lastName);
  }

  private int readAnswer() {
    return ioService.readIntWithPrompt("Enter your answer:");
  }

  private void outputQuestion(Question question) {
    ioService.out(questionConverter.convertToString(question.getId(), question));
  }

  private void saveUser(User user) {
    userService.save(user);
  }

  @Override
  public void processCommand() {
    var user = readUser();

    for (var question : questionService.listAll()) {
      outputQuestion(question);

      var answerId = readAnswer();

      var answersLen = question.getAnswers().size();
      if (answerId > answersLen || answerId < answersLen) {
        throw new AnswerIndexOutOfBoundsException("Wrong answer index");
      }

      doAnswer(question.getId(), answerId);
    }

    var score = getScore();
    user.setScore(score);

    ioService.out("Your score is " + score);
    saveUser(user);
  }

  @Override
  public MenuOption getProcessedCommandOption() {
    return processedCommandOption;
  }
}
