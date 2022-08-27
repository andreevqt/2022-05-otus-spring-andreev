package ru.otus.library.dto;

import lombok.AllArgsConstructor;
import ru.otus.library.domain.Book;

@AllArgsConstructor
public class CommentResponseDto {

  private Long id;
  private String content;
  private Book book;

}
