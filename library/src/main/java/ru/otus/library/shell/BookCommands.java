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

  @ShellMethod(value = "Create a new book", key = { "book:create", "book:insert" })
  public String create(@ShellOption String title, @ShellOption(defaultValue = ShellOption.NULL) Long genreId,
      @ShellOption(defaultValue = ShellOption.NULL) Long authorId) {
    bookService.insert(new Book(null, title, genreId, authorId));
    return "Created";
  }

  @ShellMethod(value = "Update a book", key = { "book:update" })
  public String update(@ShellOption long id, @ShellOption String title, @ShellOption(defaultValue = ShellOption.NULL) Long genreId,
      @ShellOption(defaultValue = ShellOption.NULL) Long authorId) {
    var isUpdated = bookService.update(new Book(id, title, genreId, authorId));
    return isUpdated ? "Updated" : "Couldn't find a book with id=" + id;
  }

  @ShellMethod(value = "Delete a book", key = { "book:delete", "book:del", "book:remove" })
  public String delete(@ShellOption long id) {
    var isDeleted = bookService.delete(id);
    return isDeleted ? "Deleted" : "Couldn't find a book with id=" + id;
  }

  @ShellMethod(value = "List all books", key = { "book:all", "book:findAll", "book:list" })
  public String findAll() {
    return converter.convert(bookService.findAll());
  }

}
