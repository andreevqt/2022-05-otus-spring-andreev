package ru.otus.library.mappers;

import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookRequestDto;
import ru.otus.library.dto.BookResponseDto;

import java.util.List;

public interface BookMapper {

  Book fromDto(BookRequestDto book);

  List<BookResponseDto> toDtos(List<Book> books);

  BookResponseDto toDto(Book book);

}
