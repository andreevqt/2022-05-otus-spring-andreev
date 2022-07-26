package ru.otus.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreDao extends CrudRepository<Genre, Long> {

  List<Genre> findAll();

}
