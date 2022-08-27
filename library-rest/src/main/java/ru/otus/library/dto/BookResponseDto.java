package ru.otus.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookResponseDto {

  private Long id;
  private String title;
  private AuthorDto author;
  private GenreDto genre;

}
