package ru.otus.library.service.converters;

import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreConverter {

  String convert(Genre genre);

  String convert(List<Genre> genre);

}
