package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {

  private final Long id;
  private final String title;
  private Author author;
  private Genre genre;

}
