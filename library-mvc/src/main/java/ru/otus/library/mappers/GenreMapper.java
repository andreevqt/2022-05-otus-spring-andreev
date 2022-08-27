package ru.otus.library.mappers;

import ru.otus.library.domain.Genre;
import ru.otus.library.dto.GenreDto;

import java.util.List;

public interface GenreMapper {

  GenreDto genreToGenreDto(Genre genre);

  List<GenreDto> genresToGenreDtos(List<Genre> genres);

}
