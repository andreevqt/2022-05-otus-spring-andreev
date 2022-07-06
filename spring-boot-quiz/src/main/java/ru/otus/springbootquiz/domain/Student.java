package ru.otus.springbootquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

  private final String firstName;
  private final String lastName;

}
