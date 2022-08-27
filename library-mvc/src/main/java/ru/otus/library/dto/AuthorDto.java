package ru.otus.library.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthorDto {

  private Long id;
  private String name;

}
