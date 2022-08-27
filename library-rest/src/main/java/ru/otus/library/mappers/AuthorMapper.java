package ru.otus.library.mappers;

import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorDto;

import java.util.List;

public interface AuthorMapper {


  Author fromDto(AuthorDto author);

  AuthorDto toDto(Author author);

  List<AuthorDto> toDtos(List<Author> authors);

}
