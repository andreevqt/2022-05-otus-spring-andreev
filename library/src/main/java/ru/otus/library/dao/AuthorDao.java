package ru.otus.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorDao extends CrudRepository<Author, Long> {

  List<Author> findAll();

}
