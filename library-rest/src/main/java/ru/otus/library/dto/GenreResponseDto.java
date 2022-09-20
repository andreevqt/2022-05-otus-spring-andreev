package ru.otus.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenreResponseDto {

  private Long id;

  private final String title;

}
