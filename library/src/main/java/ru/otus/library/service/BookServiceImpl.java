package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dao.BookDao;
import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookDao bookDao;

  @Transactional(readOnly = true)
  @Override
  public Optional<Book> findById(Long id) {
    return bookDao.findById(id);
  }

  @Transactional
  @Override
  public Book save(Book book) {
    return bookDao.save(book);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Book> findAll() {
    return bookDao.findAll();
  }

  @Transactional
  @Override
  public void delete(Long id) {
    bookDao.deleteById(id);
  }

}
