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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    var book = new Book(1L, "Alice's Adventures in Wonderland", null, null);
    given(bookDao.findById(book.getId())).willReturn(Optional.of(book));

    assertThat(bookService.findById(book.getId())).isEqualTo(Optional.of(book));
    verify(bookDao, times(1)).findById(book.getId());
  }

  @DisplayName("возвращать пустой optional если книга не найдена")
  @Test
  void shouldReturnEmptyOptionalIfBooknotFound() {
    assertThat(bookService.findById(1L)).isEmpty();
  }

  @DisplayName("создавать книгу")
  @Test
  void shouldCreateBook() {
    var book = new Book(5L, "Some book", null, null);
    bookService.save(book);
    verify(bookDao, times(1)).save(book);
  }

  @DisplayName("обновлять книгу")
  @Test
  void shouldUpdateBook() {
    var book = new Book(1L, "Updated book", null, null);
    bookService.save(book);
    verify(bookDao, times(1)).save(book);
  }

  @DisplayName("бросать исключение если не получилось обновить")
  @Test
  void shouldThrowIfCouldntUpdateBook() {
    var book = new Book(1L, "Some book", null, null);
    given(bookDao.save(book)).willThrow(IllegalArgumentException.class);

    assertThatThrownBy(() -> bookService.save(book)).isInstanceOf(IllegalArgumentException.class);
    verify(bookDao, times(1)).save(book);
  }

  @DisplayName("удалять книгу по id")
  @Test
  void shouldDeleteBookById() {
    var bookId = 1L;
    bookService.delete(bookId);
    verify(bookDao, times(1)).delete(bookId);
  }

  @DisplayName("бросать исключение если не получилось удалить книгу")
  @Test
  void shouldReturnFalseIfCouldntDeleteBook() {
    var bookId = 1L;
    willThrow(IllegalArgumentException.class).given(bookDao).delete(bookId);
    
    assertThatThrownBy(() -> bookService.delete(bookId)).isInstanceOf(IllegalArgumentException.class);
    verify(bookDao, times(1)).delete(bookId);
  }

}
