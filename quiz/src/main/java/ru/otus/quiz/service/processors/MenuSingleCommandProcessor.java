package ru.otus.quiz.service.processors;

import ru.otus.quiz.service.menu.MenuOption;

public interface MenuSingleCommandProcessor {
  void processCommand();

  MenuOption getProcessedCommandOption();
}