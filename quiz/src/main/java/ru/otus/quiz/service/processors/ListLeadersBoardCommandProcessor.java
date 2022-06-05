package ru.otus.quiz.service.processors;

import ru.otus.quiz.domain.User;
import ru.otus.quiz.service.IOService;
import ru.otus.quiz.service.UserConverter;
import ru.otus.quiz.service.UserService;
import ru.otus.quiz.service.menu.MenuOption;

public class ListLeadersBoardCommandProcessor implements MenuSingleCommandProcessor {

  private final UserConverter userConverter;
  private final UserService userService;
  private final IOService ioService;
  private final MenuOption processedCommandOption;

  public ListLeadersBoardCommandProcessor(UserConverter userConverter, UserService userService, IOService ioService,
                                          MenuOption processedCommandOption) {
    this.userConverter = userConverter;
    this.userService = userService;
    this.ioService = ioService;
    this.processedCommandOption = processedCommandOption;
  }

  @Override
  public void processCommand() {
    var users = userService.listAll();
    if (users.isEmpty()) {
      ioService.out("Leaders board is empty");
      return;
    }

    ioService.out("*** Leaders ***");
    users.sort(User::compareTo);
    for (int i = 0; i < users.size(); i++) {
      var user = users.get(i);
      ioService.out(userConverter.convertToString(i + 1, user));
    }
  }

  @Override
  public MenuOption getProcessedCommandOption() {
    return processedCommandOption;
  }
}
