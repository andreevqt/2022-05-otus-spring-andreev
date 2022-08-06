package ru.otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, String> {

  List<Author> findAll();

}
