package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.service.processors.BookCommandProcessor;

@AllArgsConstructor
@ShellComponent
public class BookCommands {

  private final BookCommandProcessor processor;

  @ShellMethod(value = "Find book by id", key = {"book:find", "book:findById"})
  public String find(@ShellOption long id) {
    return processor.findById(id);
  }

  @ShellMethod(value = "Create a new book", key = {"book:create", "book:insert"})
  public String create(@ShellOption String title, @ShellOption(defaultValue = "0") Long genreId,
                       @ShellOption(defaultValue = "0") Long authorId) {
    return processor.insert(title, genreId, authorId);
  }

  @ShellMethod(value = "Update a book", key = {"book:update"})
  public String update(@ShellOption long id, @ShellOption String title, @ShellOption(defaultValue = "0") Long genreId,
                       @ShellOption(defaultValue = "0") Long authorId) {
    return processor.update(id, title, genreId, authorId);
  }

  @ShellMethod(value = "Delete a book", key = {"book:delete", "book:del", "book:remove"})
  public String delete(@ShellOption long id) {
    return processor.delete(id);
  }

  @ShellMethod(value = "List all books", key = {"book:all", "book:findAll", "book:list"})
  public String findAll() {
    return processor.findAll();
  }

}
