package ru.otus.library.service;

import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

  Optional<Author> findById(Long id);

  Author save(Author author);

  List<Author> findAll();

  void delete(Long id);

}
