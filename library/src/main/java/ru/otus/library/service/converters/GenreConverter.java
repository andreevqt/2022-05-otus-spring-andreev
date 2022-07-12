package ru.otus.library.service.converters;

import java.util.List;

import ru.otus.library.domain.Genre;

public interface GenreConverter {

  String convert(Genre genre);

  String convert(List<Genre> genre);

}
