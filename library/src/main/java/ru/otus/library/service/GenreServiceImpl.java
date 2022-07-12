package ru.otus.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.otus.library.dao.GenreDao;
import ru.otus.library.domain.Genre;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreDao genreDao;

  @Override
  public Optional<Genre> findById(long id) {
    return genreDao.findById(id);
  }

  @Override
  public void insert(Genre genre) {
    genreDao.insert(genre);
  }

  @Override
  public boolean update(Genre genre) {
    return genreDao.update(genre);
  }

  @Override
  public List<Genre> findAll() {
    return genreDao.findAll();
  }

  @Override
  public boolean delete(long id) {
    return genreDao.delete(id);
  }

}
