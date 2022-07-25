package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import ru.otus.library.service.processors.CommentCommandProcessor;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@AllArgsConstructor
@ShellComponent
public class CommentCommands {

  private final CommentCommandProcessor processor;

  @ShellMethod(value = "Create comment", key = {"comment:create", "comment:insert"})
  public String insert(@ShellOption long bookId, @ShellOption String content) {
    return processor.insert(bookId, content);
  }

  @ShellMethod(value = "Find comment by id", key = {"comment:find", "comment:findById"})
  String findById(@ShellOption long id) {
    return processor.findById(id);
  }

  @ShellMethod(value = "Find comment by book id", key = {"comment:findByBookId", "comment:byBook", "comment:listByBook"})
  String findByBookId(@ShellOption long bookId) {
    return processor.findByBookId(bookId);
  }

  @ShellMethod(value = "Update a comment", key = {"comment:update"})
  String update(@ShellOption long id, @ShellOption long bookId, @ShellOption String content) {
    return processor.update(id, bookId, content);
  }

  @ShellMethod(value = "Delete a comment", key = {"comment:delete", "comment:del", "comment:remove"})
  String delete(@ShellOption long id) {
    return processor.delete(id);
  }

}
