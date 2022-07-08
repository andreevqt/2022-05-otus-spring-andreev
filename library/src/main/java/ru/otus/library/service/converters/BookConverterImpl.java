package ru.otus.library.service.converters;

import java.util.List;

import org.springframework.stereotype.Component;

import ru.otus.library.domain.Book;

@Component
public class BookConverterImpl implements BookConverter {

  @Override
  public String convert(Book book) {
    var sb = new StringBuilder("Book(");
    sb.append("id=" + book.getId());
    sb.append(", title=" + book.getTitle());

    var genre = book.getGenre();
    if (genre != null) {
      sb.append(", genre=Genre(id=" + genre.getId() + ", title=" + genre.getTitle() + ")");
    }

    var author = book.getAuthor();
    if (author != null) {
      sb.append(", author=Author(id=" + author.getId() + ", name=" + author.getName() + ")");
    }

    sb.append(")");
    return sb.toString();
  }

  @Override
  public String convert(List<Book> books) {
    return books.stream().map((book) -> convert(book)).reduce("", (acc, curr) -> acc + '\n' + curr);
  }

}
