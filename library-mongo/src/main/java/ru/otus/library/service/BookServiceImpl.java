package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final CommentRepository commentRepository;

  @Transactional(readOnly = true)
  @Override
  public List<Book> findByAuthorId(String authorId) {
    return bookRepository.findByAuthorId(authorId);
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Book> findById(String id) {
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
  public void delete(String id) {
    bookRepository.deleteById(id);
    commentRepository.deleteByBookId(id);
  }

  @Override
  public void deleteByAuthorId(String authorId) {
    bookRepository.findByAuthorId(authorId).forEach((book) -> delete(book.getId()));
  }

}
