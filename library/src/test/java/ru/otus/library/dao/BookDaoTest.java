package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с книгами должно")
@DataJpaTest
@Import(BookDao.class)
class BookDaoTest {

  private static final List<Book> BOOK_LIST = List.of(
    new Book(
        1L, "Alice's Adventures in Wonderland",
        new Author(1L, "Lewis Carroll"), new Genre(1L, "Adventures")
    ),
    new Book(
        2L, "Harry Potter and the Philosopher's Stone",
        new Author(2L, "J. K. Rowling"), new Genre(1L, "Adventures")
    ),
    new Book(
        3L, "Pride and Prejudice",
        new Author(3L, "Jane Austen"), new Genre(2L, "Novels")
    )
  );

  @Autowired
  private BookDao bookDao;

  @DisplayName("возвращать список книг")
  @Test
  void shouldReturnListOfBooks() {
    assertThat(bookDao.findAll()).usingRecursiveComparison().isEqualTo(BOOK_LIST);
  }

  @DisplayName("создавать книгу")
  @Test
  void shouldCreateBook() {
    var book = new Book(null, "Some book", null, null);
    bookDao.save(book);
    assertThat(bookDao.findById(book.getId())).usingRecursiveComparison()
      .ignoringFields("author", "genre").isEqualTo(Optional.of(book));
  }

  @DisplayName("возвращать книгу по id")
  @Test
  void shouldReturnBookById() {
    var book = BOOK_LIST.get(1);
    var res = bookDao.findById(book.getId());
    assertThat(res).isNotEmpty();
    assertThat(res.get()).usingRecursiveComparison().ignoringFields("author", "genre").isEqualTo(book);
  }

  @DisplayName("возвращать пустой Optional если книга не найдена")
  @Test
  void shouldReturnEmptyOptionalIfBookNotFound() {
    assertThat(bookDao.findById(44L)).isEmpty();
  }

  @DisplayName("обновлять книгу")
  @Test
  void shouldUpdateBook() {
    var book = new Book(1L, "Some book", null, null);
    bookDao.save(book);
    assertThat(bookDao.findById(book.getId())).usingRecursiveComparison().isEqualTo(Optional.of(book));
  }

  @DisplayName("удалять книгу по id")
  @Test
  void shouldDeleteBookById() {
    var bookId = 1L;
    bookDao.deleteById(bookId);
    assertThat(bookDao.findById(bookId).isEmpty()).isEqualTo(true);
  }

  @DisplayName("бросать исключение если не получилось удалить книгу")
  @Test
  void shouldThrowIfCouldntDeleteBook() {
    assertThatThrownBy(() -> bookDao.deleteById(100L)).isInstanceOf(IllegalArgumentException.class);
  }

}
