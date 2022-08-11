package ru.otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {

  List<Book> findAll();

  List<Book> findByAuthorId(String authorId);

  void deleteByAuthorId(String authorId);

}
