package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

  private final Long id;
  private final String title;
  private final Long genreId;
  private final Genre genre;
  private final Long authorId;
  private final Author author;

  public Book(Long id, String title) {
    this(id, title, null, null, null, null);
  }

}
