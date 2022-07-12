package ru.otus.library.service;

import java.util.List;
import java.util.Optional;

import ru.otus.library.domain.Genre;

public interface GenreService {

  Optional<Genre> findById(long id);

  void insert(Genre genre);

  boolean update(Genre genre);

  List<Genre> findAll();

  boolean delete(long id);

}
