package ru.otus.library.mappers;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Genre;
import ru.otus.library.dto.GenreDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapperImpl implements GenreMapper {

  @Override
  public GenreDto genreToGenreDto(Genre genre) {
    return new GenreDto(genre.getId(), genre.getTitle());
  }

  @Override
  public List<GenreDto> genresToGenreDtos(List<Genre> genres) {
    return genres.stream().map(this::genreToGenreDto).collect(Collectors.toList());
  }

}
