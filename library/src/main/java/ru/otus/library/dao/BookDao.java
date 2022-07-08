package ru.otus.library.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.library.domain.Book;

public interface BookDao {

  List<Book> findAll();

  Optional<Book> findById(long id);

  void insert(Book book);

  void update(Book book);

  void delete(long id);
  
}
