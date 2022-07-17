package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.Comment;
import ru.otus.library.service.CommentService;
import ru.otus.library.service.converters.CommentConverter;

@AllArgsConstructor
@ShellComponent
public class CommentCommands {

  private final CommentService commentService;
  private final CommentConverter converter;

  @ShellMethod(value = "Create comment", key = {"comment:create", "comment:insert"})
  public String insert(@ShellOption long bookId, @ShellOption String content) {
    commentService.save(new Comment(null, bookId, content));
    return "Created";
  }

  @ShellMethod(value = "Find comment by id", key = {"comment:find", "comment:findById"})
  String findById(@ShellOption long id) {
    return commentService.findById(id).map(converter::convert)
      .orElse("Comment with id=" + id + " not found");
  }

  @ShellMethod(value = "List all comments", key = {"comment:all", "comment:list", "comment:findAll"})
  String findAll() {
    return converter.convert(commentService.findAll());
  }

  @ShellMethod(value = "Update a comment", key = {"comment:update"})
  String update(@ShellOption long id, @ShellOption long bookId, @ShellOption String content) {
    try {
      commentService.save(new Comment(id, bookId, content));
      return "Updated";
    } catch (Exception e) {
      return "Couldn't update a comment with id=" + id;
    }
  }

  @ShellMethod(value = "Delete a comment", key = {"comment:delete", "comment:del", "comment:remove"})
  String delete(@ShellOption long id) {
    try {
      commentService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't delete a comment with id=" + id;
    }
  }
}
