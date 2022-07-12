package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {

  @Autowired
  private BookDaoImpl bookDao;

  @DisplayName("возвращать список книг")
  @Test
  void shouldReturnListOfBooks() {
    var expected = List.of(
      new Book(1L, "Alice's Adventures in Wonderland", 1L, 1L),
      new Book(2L, "Harry Potter and the Philosopher's Stone", 1L, 2L),
      new Book(3L, "Pride and Prejudice", 2L, 3L)
    );
    assertThat(bookDao.findAll()).isEqualTo(expected);
  }

  @DisplayName("создаваить книгу")
  @Test
  void shouldCreateBook() {
    var book = new Book(4L, "Some book", 1L, 1L);
    bookDao.insert(book);
    assertThat(bookDao.findById(book.getId())).isEqualTo(Optional.of(book));
  }

  @DisplayName("возвращать книгу по id")
  @Test
  void shouldReturnBookById() {
    var bookId = 1L;
    var expected = Optional.of(new Book(bookId, "Alice's Adventures in Wonderland", 1L, 1L));
    assertThat(bookDao.findById(bookId)).isEqualTo(expected);
  }

  @DisplayName("возвращать пустой Optional если книга не найдена")
  @Test
  void shouldReturnEmptyOptionalIfBookNotFound() {
    var bookId = 44L;
    assertThat(bookDao.findById(bookId).isEmpty()).isEqualTo(true);
  }

  @DisplayName("обновлять книгу")
  @Test
  void shouldUpdateBook() {
    var bookId = 1L;
    var expected = new Book(bookId, "Hello world!", 1L, 1L);

    assertThat(bookDao.update(expected)).isEqualTo(true);
    assertThat(bookDao.findById(bookId)).isEqualTo(Optional.of(expected));
  }

  @DisplayName("возвращать false если не получилось обновить")
  @Test
  void shouldReturnFalseIfCouldntUpdateBook() {
    var bookId = 33L;
    var expected = new Book(bookId, "Hello world!", 1L, 1L);

    assertThat(bookDao.update(expected)).isEqualTo(false);
  }

  @DisplayName("удалять книгу по id")
  @Test
  void shouldDeleteBookById() {
    var bookId = 1L;
    assertThat(bookDao.delete(bookId)).isEqualTo(true);
    assertThat(bookDao.findById(bookId).isEmpty()).isEqualTo(true);
  }

  @DisplayName("возвращать false если не получилось удалить книгу")
  @Test
  void shouldReturnFalseIfCouldntDeleteBook() {
    var bookId = 100L;
    assertThat(bookDao.delete(bookId)).isEqualTo(false);
  }

}