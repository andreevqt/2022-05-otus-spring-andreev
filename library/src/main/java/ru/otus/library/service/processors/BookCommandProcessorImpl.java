package ru.otus.library.service.processors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import ru.otus.library.domain.Book;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.GenreService;
import ru.otus.library.service.converters.BookConverter;

@Service
@AllArgsConstructor
public class BookCommandProcessorImpl implements BookCommandProcessor {

  private final BookService bookService;
  private final GenreService genreService;
  private final AuthorService authorService;
  private final BookConverter converter;

  @Transactional(readOnly = true)
  @Override
  public String findById(long id) {
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
  public String insert(String title, long genreId, long authorId) {
    var genre = genreService.findById(genreId);
    var author = authorService.findById(authorId);
    bookService.save(new Book(null, title, author.orElse(null), genre.orElse(null)));
    return "Created";
  }

  @Transactional
  @Override
  public String update(long id, String title, long genreId, long authorId) {
    var genre = genreService.findById(genreId);
    var author = authorService.findById(authorId);
    bookService.save(new Book(id, title, author.orElse(null), genre.orElse(null)));
    return "Updated";
  }

  @Override
  public String delete(long id) {
    try {
      bookService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't find a book with id=" + id;
    }
  }
  
}
