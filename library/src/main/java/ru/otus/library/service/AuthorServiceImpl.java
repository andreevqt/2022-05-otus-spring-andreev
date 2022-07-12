package ru.otus.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.domain.Author;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorDao authorDao;

  @Override
  public Optional<Author> findById(long id) {
    return authorDao.findById(id);
  }

  @Override
  public void insert(Author author) {
    authorDao.insert(author);
  }

  @Override
  public boolean update(Author author) {
    return authorDao.update(author);
  }

  @Override
  public List<Author> findAll() {
    return authorDao.findAll();
  }

  @Override
  public boolean delete(long id) {
    return authorDao.delete(id);
  }
  
}
