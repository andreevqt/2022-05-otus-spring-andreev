package ru.otus.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.exceptions.AnswerIndexOutOfBoundsException;
import ru.otus.quiz.exceptions.MenuCommandProcessorNotFound;
import ru.otus.quiz.exceptions.MenuItemIndexOutOfBoundsException;
import ru.otus.quiz.service.menu.MenuOptionsRegistry;
import ru.otus.quiz.service.processors.MenuCommandsProcessor;

@Service
public class App {

  private final IOService ioService;
  private final MenuOptionsRegistry menuOptionsRegistry;
  private final MenuCommandsProcessor commandsProcessor;

  @Autowired
  public App(IOService ioService, MenuOptionsRegistry menuOptionsRegistry,
             MenuCommandsProcessor commandsProcessor) {
    this.ioService = ioService;
    this.menuOptionsRegistry = menuOptionsRegistry;
    this.commandsProcessor = commandsProcessor;
  }

  private void outputMenu() {
    ioService.out("Select option...");
    menuOptionsRegistry.getAvailableMenuOptions().stream()
      .sorted()
      .map(m -> m.getId() + ". " + m.getDescription())
      .forEach(ioService::out);
  }

  private int readSelectedOptionNumber() {
    return ioService.readInt();
  }

  public void run() {
    outputMenu();
    try {
      var selectedMenuItem = readSelectedOptionNumber();
      processMenuCommand(selectedMenuItem);

    } catch (NumberFormatException e) {
      ioService.out("Number format error");
    } catch (MenuItemIndexOutOfBoundsException e) {
      ioService.out("Wrong option number");
    } catch (MenuCommandProcessorNotFound e) {
      ioService.out("Menu handler not found");
    } catch (AnswerIndexOutOfBoundsException e) {
      ioService.out("Wrong answer's index");
    }
  }

  private void processMenuCommand(int selectedMenuItemId) {
    var selectedMenuOption = menuOptionsRegistry.getMenuOptionById(selectedMenuItemId)
      .orElseThrow(() -> new MenuItemIndexOutOfBoundsException("Given menu item index is out of range"));

    commandsProcessor.processMenuCommand(selectedMenuOption);
  }
}
