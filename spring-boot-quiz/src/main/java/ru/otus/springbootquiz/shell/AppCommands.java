package ru.otus.springbootquiz.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.AllArgsConstructor;
import ru.otus.springbootquiz.service.App;

@AllArgsConstructor
@ShellComponent
public class AppCommands {

  private final App app;

  @ShellMethod(value = "Start quiz command", key = { "start", "run", "s", "r" })
  public void start() {
    app.run();
  }

}
