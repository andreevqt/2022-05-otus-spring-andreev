package ru.otus.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.AllArgsConstructor;
import ru.otus.library.domain.Book;
import ru.otus.library.service.BookService;
import ru.otus.library.service.converters.BookConverter;

@AllArgsConstructor
@ShellComponent
public class BookCommands {

  private final BookService bookService;
  private final BookConverter converter;

  @ShellMethod(value = "Find book by id", key = { "book:find", "book:findById" })
  public String find(@ShellOption long id) {
    return bookService.findById(id).map((book) -> converter.convert(book)).orElse("Book not found");
  }

  @ShellMethod(value = "Create new book", key = { "book:create", "book:insert" })
  public void create(@ShellOption String title, @ShellOption(defaultValue = ShellOption.NULL) Long genreId,
      @ShellOption(defaultValue = ShellOption.NULL) Long authorId) {
    bookService.insert(new Book(null, title, genreId, null, authorId, null));
  }

  @ShellMethod(value = "Update book", key = { "book:update" })
  public void update(@ShellOption long id, @ShellOption String title, @ShellOption long genreId,
      @ShellOption long authorId) {
    bookService.update(new Book(id, title, genreId, null, authorId, null));
  }

  @ShellMethod(value = "Delete book", key = { "book:delete" })
  public void delete(@ShellOption long id) {
    bookService.delete(id);
  }

  @ShellMethod(value = "List all books", key = { "book:all", "book:list" })
  public String findAll() {
    return converter.convert(bookService.findAll());
  }

}
