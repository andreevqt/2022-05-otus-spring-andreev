package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.repository.GenreRepository;
import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements ru.otus.library.service.GenreService {

  private final GenreRepository genreRepository;

  @Transactional(readOnly = true)
  @Override
  public Optional<Genre> findById(Long id) {
    return genreRepository.findById(id);
  }

  @Transactional
  @Override
  public Genre save(Genre genre) {
    return genreRepository.save(genre);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Genre> findAll() {
    return genreRepository.findAll();
  }

  @Transactional
  @Override
  public void delete(Long id) {
    genreRepository.deleteById(id);
  }

}
