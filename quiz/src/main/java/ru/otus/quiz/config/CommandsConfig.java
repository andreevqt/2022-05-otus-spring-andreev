package ru.otus.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.quiz.domain.User;
import ru.otus.quiz.service.*;
import ru.otus.quiz.service.menu.MenuOption;
import ru.otus.quiz.service.menu.MenuOptionsRegistry;
import ru.otus.quiz.service.menu.MenuOptionsRegistryImpl;
import ru.otus.quiz.service.processors.ListLeadersBoardCommandProcessor;
import ru.otus.quiz.service.processors.MenuCommandsProcessor;
import ru.otus.quiz.service.processors.MenuCommandsProcessorImpl;
import ru.otus.quiz.service.processors.StartQuizCommandProcessor;

import java.util.Comparator;
import java.util.List;

@Configuration
@ComponentScan(value = "ru.otus.quiz")
public class CommandsConfig {

  private final MenuOption startQuiz;
  private final MenuOption listLeadersBoard;

  CommandsConfig() {
    startQuiz = new MenuOption(1, "Start quiz");
    listLeadersBoard = new MenuOption(2, "List leaders board");
  }

  @Bean
  MenuOptionsRegistry menuOptionsRegistry() {
    return new MenuOptionsRegistryImpl(List.of(startQuiz, listLeadersBoard));
  }

  @Bean
  MenuCommandsProcessor commandsProcessor(QuestionConverter questionConverter,
                                          IOService ioService, QuestionService questionService,
                                          UserService userService, UserConverter userConverter, Comparator<User> userComparator) {
    var startQuizProcessor = new StartQuizCommandProcessor(questionService,
      startQuiz, questionConverter, ioService, userService);

    var listLeadersBoardProcessor = new ListLeadersBoardCommandProcessor(userConverter, userService,
      ioService, listLeadersBoard, userComparator);

    return new MenuCommandsProcessorImpl(List.of(startQuizProcessor, listLeadersBoardProcessor));
  }
}
