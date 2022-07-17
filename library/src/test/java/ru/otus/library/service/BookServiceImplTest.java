package ru.otus.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.library.dao.BookDao;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

@DisplayName("Сервис для работы с книгами должен")
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  @Mock
  private BookDao bookDao;
  @InjectMocks
  private BookServiceImpl bookService;

  @DisplayName("возвращать список книг")
  @Test
  void shouldReturnListOfBook() {
    var expected = List.of(
      new Book(1L, "Alice's Adventures in Wonderland", new Author(1L, "Lewis Carroll"), new Genre(1L, "Adventures")),
      new Book(2L, "Harry Potter and the Philosopher's Stone", new Author(2L, "J. K. Rowling"), new Genre(1L, "Adventures")),
      new Book(3L, "Pride and Prejudice", new Author(2L, "Jane Austen"), new Genre(2L, "Novels"))
    );
    given(bookDao.findAll()).willReturn(expected);

    assertThat(bookService.findAll()).isEqualTo(expected);
    verify(bookDao, times(1)).findAll();
  }

  @DisplayName("возвращать книгу по id")
  @Test
  void shouldFindBookById() {
    var book = new Book(1L, "Alice's Adventures in Wonderland");
    given(bookDao.findById(book.getId())).willReturn(Optional.of(book));

    assertThat(bookService.findById(book.getId())).isEqualTo(Optional.of(book));
    verify(bookDao, times(1)).findById(book.getId());
  }

  @DisplayName("возвращать пустой optional если книга не найдена")
  @Test
  void shouldReturnEmptyOptionalIfBooknotFound() {
    assertThat(bookService.findById(1L).isEmpty()).isEqualTo(true);
  }

  @DisplayName("создавать книгу")
  @Test
  void shouldCreateBook() {
    var book = new Book(5L, "Some book");

    bookService.save(book);

    verify(bookDao, times(1)).insert(book);
  }

  @DisplayName("обновлять книгу")
  @Test
  void shouldUpdateBook() {
    var book = new Book(1L, "Some book");
    given(bookDao.update(book)).willReturn(true);

    assertThat(bookService.update(book)).isEqualTo(true);
    verify(bookDao, times(1)).update(book);
  }

  @DisplayName("возвращать false если не получилось обновить")
  @Test
  void shouldReturnFalseIfCouldntUpdateBook() {
    var book = new Book(1L, "Some book");

    assertThat(bookService.update(book)).isEqualTo(false);
    verify(bookDao, times(1)).update(book);
  }

  @DisplayName("удалять книгу по id")
  @Test
  void shouldDeleteBookById() {
    var bookId = 1L;
    given(bookDao.delete(bookId)).willReturn(true);

    assertThat(bookService.delete(bookId)).isEqualTo(true);
    verify(bookDao, times(1)).delete(bookId);
  }

  @DisplayName("возвращать false если не получилось удалить книгу")
  @Test
  void shouldReturnFalseIfCouldntDeleteBook() {
    var bookId = 1L;
    assertThat(bookService.delete(bookId)).isEqualTo(false);
    verify(bookDao, times(1)).delete(bookId);
  }

}
