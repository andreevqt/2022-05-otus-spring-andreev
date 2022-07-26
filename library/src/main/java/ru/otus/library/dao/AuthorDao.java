package ru.otus.library.dao;

import ru.otus.library.domain.Author;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuthorDao extends CrudRepository<Author, Long> {

  List<Author> findAll();

}
