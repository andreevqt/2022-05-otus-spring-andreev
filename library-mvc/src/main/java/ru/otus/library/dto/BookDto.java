package ru.otus.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
public class BookDto {

  private Long id;
  @NotBlank
  @Size(min = 2, max = 30)
  private String title;
  private Long authorId;
  private Long genreId;

}