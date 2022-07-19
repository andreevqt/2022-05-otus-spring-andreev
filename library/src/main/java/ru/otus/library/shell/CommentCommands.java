package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Comment;
import ru.otus.library.service.BookService;
import ru.otus.library.service.CommentService;
import ru.otus.library.service.converters.CommentConverter;

@AllArgsConstructor
@ShellComponent
public class CommentCommands {

  private final CommentService commentService;
  private final BookService bookService;
  private final CommentConverter converter;

  @ShellMethod(value = "Create comment", key = {"comment:create", "comment:insert"})
  public String insert(@ShellOption long bookId, @ShellOption String content) {
    var book = bookService.findById(bookId);
    commentService.save(new Comment(null, book.orElse(null), content));
    return "Created";
  }

  @Transactional(readOnly = true)
  @ShellMethod(value = "Find comment by id", key = {"comment:find", "comment:findById"})
  String findById(@ShellOption long id) {
    return commentService.findById(id).map(converter::convert)
      .orElse("Comment with id=" + id + " not found");
  }

  @Transactional(readOnly = true)
  @ShellMethod(value = "Find comment by book id", key = {"comment:findByBookId", "comment:byBook", "comment:listByBook"})
  String findByBookId(@ShellOption long bookId) {
    return converter.convert(commentService.findByBookId(bookId));
  }

  @ShellMethod(value = "Update a comment", key = {"comment:update"})
  String update(@ShellOption long id, @ShellOption long bookId, @ShellOption String content) {
    var book = bookService.findById(bookId);
    commentService.save(new Comment(id, book.orElse(null), content));
    return "Updated";
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
