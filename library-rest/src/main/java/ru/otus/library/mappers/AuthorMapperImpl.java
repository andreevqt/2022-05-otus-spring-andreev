package ru.otus.library.mappers;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorRequestDto;
import ru.otus.library.dto.AuthorResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapperImpl implements AuthorMapper {

  @Override
  public Author fromDto(AuthorRequestDto author) {
    return new Author(null, author.getName());
  }

  @Override
  public AuthorResponseDto toDto(Author author) {
    return author != null ? new AuthorResponseDto(author.getId(), author.getName()) : null;
  }

  @Override
  public List<AuthorResponseDto> toDtos(List<Author> authors) {
    return authors.stream().map(this::toDto).collect(Collectors.toList());
  }

}
