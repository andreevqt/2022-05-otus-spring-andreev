package ru.otus.library.service.converters;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookConverterImpl implements BookConverter {

  @Override
  public String convert(Book book) {
    var author = book.getAuthor();
    var genre = book.getGenre();
    var comments = book.getComments();
    var commentsList = comments != null && !comments.isEmpty()
      ? comments.stream().map(Comment::getContent).collect(Collectors.joining(", "))
      : "";

    return "Book(id=" + book.getId() + ", title=" + book.getTitle() + ", author=" +
      (author != null ? author.getName() : "null") + ", genre=" + (genre != null ? genre.getTitle() : "null") +
      ", comments=[" + commentsList + "]" + ")";
  }

  @Override
  public String convert(List<Book> books) {
    return books.stream().map(this::convert).collect(Collectors.joining("\n"));
  }

}
