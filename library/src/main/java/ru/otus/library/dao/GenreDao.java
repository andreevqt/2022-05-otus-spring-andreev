package ru.otus.library.dao;

import ru.otus.library.domain.Genre;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GenreDao extends CrudRepository<Genre, Long> {

  List<Genre> findAll();

}
