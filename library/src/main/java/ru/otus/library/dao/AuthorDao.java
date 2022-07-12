package ru.otus.library.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.library.domain.Author;

public interface AuthorDao {

  List<Author> findAll();

  Optional<Author> findById(long id);

  void insert(Author author);

  boolean update(Author author);

  boolean delete(long id);

}
