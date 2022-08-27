package ru.otus.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenreDto {

  private final Long id;
  private final String title;

}
