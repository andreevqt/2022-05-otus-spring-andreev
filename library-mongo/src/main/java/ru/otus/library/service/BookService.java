package ru.otus.library.service;

import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

  List<Book> findByAuthorId(String authorId);

  Optional<Book> findById(String id);

  Book save(Book book);

  List<Book> findAll();

  void delete(String id);

  void deleteByAuthorId(String id);

}
