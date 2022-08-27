package ru.otus.library.mappers;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Author;
import ru.otus.library.dto.AuthorDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapperImpl implements AuthorMapper {

  @Override
  public AuthorDto authorToAuthorDto(Author author) {
    return new AuthorDto(author.getId(), author.getName());
  }

  @Override
  public List<AuthorDto> authorsToAuthorDtos(List<Author> authors) {
    return authors.stream().map(this::authorToAuthorDto).collect(Collectors.toList());
  }

}
