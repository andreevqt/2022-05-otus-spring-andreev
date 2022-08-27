package ru.otus.library.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookMapperImpl implements BookMapper {

  private final AuthorService authorService;
  private final GenreService genreService;

  @Override
  public BookDto bookToBookDto(Book book) {
    return new BookDto(
      book.getId(), book.getTitle(),
      book.getAuthor().getId(), book.getGenre().getId()
    );
  }

  @Override
  public List<BookDto> booksToBookDtos(List<Book> books) {
    return books.stream().map(this::bookToBookDto).collect(Collectors.toList());
  }

  @Override
  public Book bookDtoToBook(BookDto book) {
    return new Book(
      book.getId(),
      book.getTitle(),
      authorService.findById(book.getAuthorId()).orElse(null),
      genreService.findById(book.getGenreId()).orElse(null)
    );
  }

}

