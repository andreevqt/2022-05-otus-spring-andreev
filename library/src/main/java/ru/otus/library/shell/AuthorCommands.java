package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.service.processors.AuthorCommandProcessor;

@AllArgsConstructor
@ShellComponent
public class AuthorCommands {

  private final AuthorCommandProcessor processor;

  @ShellMethod(value = "Create author", key = {"author:create", "author:insert"})
  String create(@ShellOption String name) {
    return processor.insert(name);
  }

  @ShellMethod(value = "Find author by id", key = {"author:find", "author:findById"})
  String findById(@ShellOption long id) {
    return processor.findById(id);
  }

  @ShellMethod(value = "List all authors", key = {"author:all", "author:list", "author:findAll"})
  String findAll() {
    return processor.findAll();
  }

  @ShellMethod(value = "Update an author", key = {"author:update"})
  String update(@ShellOption long id, @ShellOption String name) {
    return processor.update(id, name);
  }

  @ShellMethod(value = "Delete an author", key = {"author:delete", "author:del", "author:remove"})
  String delete(@ShellOption long id) {
    return processor.delete(id);
  }

}
