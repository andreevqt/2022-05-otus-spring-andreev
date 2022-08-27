package ru.otus.library.service;

import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

  Optional<Genre> findById(Long id);

  Genre save(Genre genre);

  List<Genre> findAll();

  void delete(Long id);

}
