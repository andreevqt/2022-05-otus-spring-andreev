package ru.otus.library.mappers;

import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorDto;

import java.util.List;

public interface AuthorMapper {

  AuthorDto authorToAuthorDto(Author author);

  List<AuthorDto> authorsToAuthorDtos(List<Author> authors);

}
