package ru.otus.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.library.dao.BookDao;
import ru.otus.library.domain.Book;

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
        new Book(1L, "Alice's Adventures in Wonderland", 1L, 1L),
        new Book(2L, "Harry Potter and the Philosopher's Stone", 1L, 2L),
        new Book(3L, "Pride and Prejudice", 2L, 3L));
    given(bookDao.findAll()).willReturn(expected);

    assertThat(bookService.findAll()).isEqualTo(expected);
    verify(bookDao, times(1)).findAll();
  }

  @DisplayName("возвращать книгу по id")
  @Test
  void shouldFindBookByIf() {
    var book = Optional.of(new Book(1L, "Alice's Adventures in Wonderland", 1L, 1L));
    given(bookDao.findById(1L)).willReturn(book);

    assertThat(bookService.findById(1L)).isEqualTo(book);
    verify(bookDao, times(1)).findById(1L);
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
    bookService.insert(book);

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
    given(bookDao.delete(1L)).willReturn(true);

    assertThat(bookService.delete(1L)).isEqualTo(true);
    verify(bookDao, times(1)).delete(1L);
  }

  @DisplayName("возвращать false если не получилось удалить книгу")
  @Test
  void shouldReturnFalseIfCouldntDeleteBook() {
    assertThat(bookService.delete(1L)).isEqualTo(false);
    verify(bookDao, times(1)).delete(1L);
  }

}
