package ru.otus.library.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
public class AuthorRequestDto {

  @NotBlank
  private String name;

}
