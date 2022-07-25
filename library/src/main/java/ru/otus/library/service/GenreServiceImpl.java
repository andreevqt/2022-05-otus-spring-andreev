package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dao.GenreDao;
import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreDao genreDao;

  @Transactional(readOnly = true)
  @Override
  public Optional<Genre> findById(Long id) {
    return genreDao.findById(id);
  }

  @Transactional
  @Override
  public Genre save(Genre genre) {
    return genreDao.save(genre);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Genre> findAll() {
    return genreDao.findAll();
  }

  @Transactional
  @Override
  public void delete(Long id) {
    genreDao.delete(id);
  }

}
