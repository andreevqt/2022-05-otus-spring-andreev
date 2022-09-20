package ru.otus.library.mappers;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Genre;
import ru.otus.library.dto.GenreResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapperImpl implements GenreMapper {

  @Override
  public Genre fromDto(GenreResponseDto genre) {
    return new Genre(null, genre.getTitle());
  }

  @Override
  public GenreResponseDto toDto(Genre genre) {
    return genre != null ? new GenreResponseDto(genre.getId(), genre.getTitle()) : null;
  }

  @Override
  public List<GenreResponseDto> toDtos(List<Genre> genres) {
    return genres.stream().map(this::toDto).collect(Collectors.toList());
  }

}
