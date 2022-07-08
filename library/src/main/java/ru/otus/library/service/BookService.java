package ru.otus.library.service;

import java.util.List;
import java.util.Optional;

import ru.otus.library.domain.Book;

public interface BookService {

  Optional<Book> findById(Long id);

  void insert(Book book);

  void update(Book book);

  List<Book> findAll();

  void delete(long id);
  
}
