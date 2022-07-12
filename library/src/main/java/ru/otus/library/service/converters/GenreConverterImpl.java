package ru.otus.library.service.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ru.otus.library.domain.Genre;

@Component
class GenreConverterImpl implements GenreConverter{

  @Override
  public String convert(Genre genre) {
    return "Genre(id=" + genre.getId() + ", title=" + genre.getTitle() + ")";
  }

  @Override
  public String convert(List<Genre> genres) {
    return genres.stream().map((genre) -> convert(genre)).collect(Collectors.joining("\n"));
  }

}
