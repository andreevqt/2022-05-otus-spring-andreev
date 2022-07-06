package ru.otus.springbootquiz.domain;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Answer {

  private final String text;
  private boolean correct ;

}
