package ru.otus.library.service;

import java.util.List;
import java.util.Optional;

import ru.otus.library.domain.Author;

public interface AuthorService {

  Optional<Author> findById(long id);

  void insert(Author author);

  boolean update(Author author);

  List<Author> findAll();

  boolean delete(long id);
  
}
