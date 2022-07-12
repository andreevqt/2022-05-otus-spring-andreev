package ru.otus.library.dao;

import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

  List<Book> findAll();

  Optional<Book> findById(long id);

  void insert(Book book);

  boolean update(Book book);

  boolean delete(long id);

}
