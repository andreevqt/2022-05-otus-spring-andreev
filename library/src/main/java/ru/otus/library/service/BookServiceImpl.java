package ru.otus.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.otus.library.dao.BookDao;
import ru.otus.library.domain.Book;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookDao bookDao;

  @Override
  public Optional<Book> findById(Long id) {
    return bookDao.findById(id);
  }

  @Override
  public void insert(Book book) {
    bookDao.insert(book);
  }

  @Override
  public List<Book> findAll() {
    return bookDao.findAll();
  }

  @Override
  public boolean update(Book book) {
    return bookDao.update(book);
  }

  @Override
  public boolean delete(long id) {
    return bookDao.delete(id);
  }
  
}
