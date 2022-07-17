package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.Book;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.GenreService;
import ru.otus.library.service.converters.BookConverter;

@AllArgsConstructor
@ShellComponent
public class BookCommands {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final BookConverter converter;

  @ShellMethod(value = "Find book by id", key = {"book:find", "book:findById"})
  public String find(@ShellOption long id) {
    return bookService.findById(id).map(converter::convert).orElse("Book not found");
  }

  @ShellMethod(value = "Create a new book", key = {"book:create", "book:insert"})
  public String create(@ShellOption String title, @ShellOption(defaultValue = "0") Long genreId,
                       @ShellOption(defaultValue = "0") Long authorId) {
    var genre = genreService.findById(genreId);
    var author = authorService.findById(authorId);
    bookService.save(new Book(null, title, author.orElse(null), genre.orElse(null)));
    return "Created";
  }

  @ShellMethod(value = "Update a book", key = {"book:update"})
  public String update(@ShellOption long id, @ShellOption String title, @ShellOption(defaultValue = "0") Long genreId,
                       @ShellOption(defaultValue = "0") Long authorId) {
    try {
      var genre = genreService.findById(genreId);
      var author = authorService.findById(authorId);
      bookService.save(new Book(id, title, author.orElse(null), genre.orElse(null)));
      return "Updated";
    } catch (Exception e) {
      return "Couldn't find a book with id=" + id;
    }
  }

  @ShellMethod(value = "Delete a book", key = {"book:delete", "book:del", "book:remove"})
  public String delete(@ShellOption long id) {
    try {
      bookService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't find a book with id=" + id;
    }
  }

  @ShellMethod(value = "List all books", key = {"book:all", "book:findAll", "book:list"})
  public String findAll() {
    return converter.convert(bookService.findAll());
  }

}
