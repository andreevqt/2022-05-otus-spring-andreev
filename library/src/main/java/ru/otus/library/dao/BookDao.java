package ru.otus.library.dao;

import ru.otus.library.domain.Book;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface BookDao extends CrudRepository<Book, Long> {

  @EntityGraph(attributePaths = {"author","genre"})
  List<Book> findAll();

}
