package ru.otus.library.mappers;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Genre;
import ru.otus.library.dto.GenreDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapperImpl implements GenreMapper {

  @Override
  public Genre fromDto(GenreDto genre) {
    return new Genre(null, genre.getTitle());
  }

  @Override
  public GenreDto toDto(Genre genre) {
    return genre != null ? new GenreDto( genre.getTitle()) : null;
  }

  @Override
  public List<GenreDto> toDtos(List<Genre> genres) {
    return genres.stream().map(this::toDto).collect(Collectors.toList());
  }

}
