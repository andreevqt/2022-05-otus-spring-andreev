package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.service.processors.GenreCommandProcessor;

@AllArgsConstructor
@ShellComponent
public class GenreCommands {

  private final GenreCommandProcessor processor;

  @ShellMethod(value = "Create a genre", key = {"genre:create", "genre:insert"})
  String create(@ShellOption String title) {
    return processor.insert(title);
  }

  @ShellMethod(value = "Find genre by id", key = {"genre:find", "genre:findById"})
  String findById(@ShellOption Long id) {
    return processor.findById(id);
  }

  @ShellMethod(value = "List all genres", key = {"genre:all", "genre:list", "genre:findAll"})
  String findAll() {
    return processor.findAll();
  }

  @ShellMethod(value = "Update a genre", key = {"genre:update"})
  String update(@ShellOption long id, @ShellOption String title) {
    return processor.update(id, title);
  }

  @ShellMethod(value = "Delete a genre", key = {"genre:delete", "genre:del", "genre:remove"})
  String delete(@ShellOption long id) {
    return processor.delete(id);
  }

}
