package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorDao authorDao;

  @Transactional(readOnly = true)
  @Override
  public Optional<Author> findById(Long id) {
    return authorDao.findById(id);
  }

  @Transactional
  @Override
  public Author save(Author author) {
    return authorDao.save(author);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Author> findAll() {
    return authorDao.findAll();
  }

  @Transactional
  @Override
  public void delete(Long id) {
    authorDao.delete(id);
  }

}
