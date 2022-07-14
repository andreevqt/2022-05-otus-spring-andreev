package ru.otus.library.service.converters;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Component
class GenreConverterImpl implements GenreConverter {

  @Override
  public String convert(Genre genre) {
    return "Genre(id=" + genre.getId() + ", title=" + genre.getTitle() + ")";
  }

  @Override
  public String convert(List<Genre> genres) {
    return genres.stream().map(this::convert).collect(Collectors.joining("\n"));
  }

}
