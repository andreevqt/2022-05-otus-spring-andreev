package ru.otus.library.service;

import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

  Optional<Book> findById(Long id);

  void insert(Book book);

  boolean update(Book book);

  List<Book> findAll();

  boolean delete(Long id);

}
