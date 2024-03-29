package ru.otus.library.service.processors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.converters.BookConverter;

@Service
@AllArgsConstructor
public class BookCommandProcessorImpl implements BookCommandProcessor {

  private final BookService bookService;
  private final AuthorService authorService;
  private final BookConverter converter;

  @Transactional(readOnly = true)
  @Override
  public String findById(String id) {
    return bookService.findById(id).map(converter::convert)
      .orElse("Book not found");
  }

  @Transactional(readOnly = true)
  @Override
  public String findAll() {
    return converter.convert(bookService.findAll());
  }
  
  @Transactional
  @Override
  public String insert(String title, String genre, String authorId) {
    var author = authorService.findById( authorId);
    bookService.save(new Book(null, title, author.orElse(null), new Genre(genre)));
    return "Created";
  }

  @Transactional
  @Override
  public String update(String id, String title, String genre, String authorId) {
    var author = authorService.findById(authorId);
    bookService.save(new Book(id, title, author.orElse(null), new Genre(genre)));
    return "Updated";
  }

  @Override
  public String delete(String id) {
    try {
      bookService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't find a book with id=" + id;
    }
  }
  
}
