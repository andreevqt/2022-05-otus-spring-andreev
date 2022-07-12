package ru.otus.library.service.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ru.otus.library.domain.Book;

@Component
public class BookConverterImpl implements BookConverter {

  @Override
  public String convert(Book book) {
    return "Book(id=" + book.getId() + ", title=" + book.getTitle() + ", authorId=" + book.getAuthorId() + ", genreId=" + book.getGenreId()  + ")";
  }

  @Override
  public String convert(List<Book> books) {
    return books.stream().map((book) -> convert(book)).collect(Collectors.joining("\n"));
  }

}
