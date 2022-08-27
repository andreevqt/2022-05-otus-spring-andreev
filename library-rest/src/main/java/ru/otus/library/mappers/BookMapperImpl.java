package ru.otus.library.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookRequestDto;
import ru.otus.library.dto.BookResponseDto;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class BookMapperImpl implements BookMapper {

  private final AuthorService authorService;
  private final GenreService genreService;
  private final AuthorMapper authorMapper;
  private final GenreMapper genreMapper;

  @Override
  public Book fromDto(BookRequestDto book) {
    return new Book(
      null, book.getTitle(),
      authorService.findById(book.getAuthorId()).orElse(null),
      genreService.findById(book.getGenreId()).orElse(null)
    );
  }

  @Override
  public List<BookResponseDto> toDtos(List<Book> books) {
    return books.stream().map(this::toDto).collect(Collectors.toList());
  }

  @Override
  public BookResponseDto toDto(Book book) {
    return new BookResponseDto(
      book.getId(), book.getTitle(),
      authorMapper.toDto(book.getAuthor()),
      genreMapper.toDto(book.getGenre())
    );
  }
}

