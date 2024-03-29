package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;
import ru.otus.library.repository.AuthorRepository;
import ru.otus.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private final BookService bookService;

  @Transactional(readOnly = true)
  @Override
  public Optional<Author> findById(String id) {
    return authorRepository.findById(id);
  }

  @Transactional
  @Override
  public Author save(Author author) {
    return authorRepository.save(author);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Author> findAll() {
    return authorRepository.findAll();
  }

  @Transactional
  @Override
  public void delete(String id) {
    authorRepository.deleteById(id);
    bookService.deleteByAuthorId(id);
  }

}
