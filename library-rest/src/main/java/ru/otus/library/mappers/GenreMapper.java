package ru.otus.library.mappers;

import ru.otus.library.domain.Genre;
import ru.otus.library.dto.GenreResponseDto;

import java.util.List;

public interface GenreMapper {

  Genre fromDto(GenreResponseDto genre);

  GenreResponseDto toDto(Genre genre);

  List<GenreResponseDto> toDtos(List<Genre> genres);

}
