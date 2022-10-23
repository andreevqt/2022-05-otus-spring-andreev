package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Transactional(readOnly = true)
  @Override
  public Optional<Book> findById(Long id) {
    return bookRepository.findById(id);
  }

  @Transactional
  @Override
  public Book save(Book book) {
    return bookRepository.save(book);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Transactional
  @Override
  public void delete(Long id) {
    bookRepository.deleteById(id);
  }

}
