package ru.otus.library.mappers;

import ru.otus.library.domain.Book;
import ru.otus.library.dto.BookDto;

import java.util.List;

public interface BookMapper {

  BookDto bookToBookDto(Book book);

  List<BookDto> booksToBookDtos(List<Book> books);

  Book bookDtoToBook(BookDto book);

}
