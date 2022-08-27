package ru.otus.library.mappers;

import ru.otus.library.domain.Genre;
import ru.otus.library.dto.GenreDto;

import java.util.List;

public interface GenreMapper {

  Genre fromDto(GenreDto genre);

  GenreDto toDto(Genre genre);

  List<GenreDto> toDtos(List<Genre> genres);

}
