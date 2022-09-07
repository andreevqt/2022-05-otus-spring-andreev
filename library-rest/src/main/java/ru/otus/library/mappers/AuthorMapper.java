package ru.otus.library.mappers;

import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorRequestDto;

import java.util.List;

public interface AuthorMapper {


  Author fromDto(AuthorRequestDto author);

  AuthorRequestDto toDto(Author author);

  List<AuthorRequestDto> toDtos(List<Author> authors);

}
