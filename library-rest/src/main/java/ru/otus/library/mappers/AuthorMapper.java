package ru.otus.library.mappers;

import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorRequestDto;
import ru.otus.library.dto.AuthorResponseDto;

import java.util.List;

public interface AuthorMapper {


  Author fromDto(AuthorRequestDto author);

  AuthorResponseDto toDto(Author author);

  List<AuthorResponseDto> toDtos(List<Author> authors);

}
