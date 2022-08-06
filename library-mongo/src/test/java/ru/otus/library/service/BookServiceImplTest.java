package ru.otus.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import ru.otus.library.repository.BookRepository;

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
  private BookRepository bookRepository;
  @InjectMocks
  private BookServiceImpl bookService;

  @DisplayName("возвращать список книг")
  @Test
  void shouldReturnListOfBook() {
    var expected = List.of(
      new Book(
        "62ee408b1fb06861d91fee40", "Alice's Adventures in Wonderland",
        new Author("62ee408b1fb06861d91fee50", "Lewis Carroll"), new Genre("Adventures")
      ),
      new Book(
        "62ee408b1fb06861d91fee41", "Harry Potter and the Philosopher's Stone",
        new Author("62ee408b1fb06861d91fee51", "J. K. Rowling"), new Genre("Adventures")
      ),
      new Book(
        "62ee408b1fb06861d91fee42", "Pride and Prejudice",
        new Author("62ee408b1fb06861d91fee52", "Jane Austen"), new Genre("Novels")
      )
    );
    given(bookRepository.findAll()).willReturn(expected);
    assertThat(bookService.findAll()).isEqualTo(expected);
    verify(bookRepository, times(1)).findAll();
  }

  @DisplayName("возвращать книгу по id")
  @Test
  void shouldFindBookById() {
    var book = new Book("62ee408b1fb06861d91fee43", "Alice's Adventures in Wonderland", null, null);
    given(bookRepository.findById(book.getId())).willReturn(Optional.of(book));
    assertThat(bookService.findById(book.getId())).isEqualTo(Optional.of(book));
    verify(bookRepository, times(1)).findById(book.getId());
  }

  @DisplayName("возвращать пустой optional если книга не найдена")
  @Test
  void shouldReturnEmptyOptionalIfBooknotFound() {
    assertThat(bookService.findById("62ee408b1fb06861d91fee44")).isEmpty();
  }

  @DisplayName("создавать книгу")
  @Test
  void shouldCreateBook() {
    var book = new Book("62ee408b1fb06861d91fee40", "Some book", null, null);
    bookService.save(book);
    verify(bookRepository, times(1)).save(book);
  }

  @DisplayName("обновлять книгу")
  @Test
  void shouldUpdateBook() {
    var book = new Book("62ee408b1fb06861d91fee40", "Updated book", null, null);
    bookService.save(book);
    verify(bookRepository, times(1)).save(book);
  }

  @DisplayName("бросать исключение если не получилось обновить")
  @Test
  void shouldThrowIfCouldntUpdateBook() {
    var book = new Book("62ee408b1fb06861d91fee40", "Some book", null, null);
    given(bookRepository.save(book)).willThrow(IllegalArgumentException.class);
    assertThatThrownBy(() -> bookService.save(book)).isInstanceOf(IllegalArgumentException.class);
    verify(bookRepository, times(1)).save(book);
  }

  @DisplayName("удалять книгу по id")
  @Test
  void shouldDeleteBookById() {
    var bookId = "62ee408b1fb06861d91fee40";
    bookService.delete(bookId);
    verify(bookRepository, times(1)).deleteById(bookId);
  }

  @DisplayName("бросать исключение если не получилось удалить книгу")
  @Test
  void shouldReturnFalseIfCouldntDeleteBook() {
    var bookId = "62ee408b1fb06861d91fee40";
    willThrow(IllegalArgumentException.class).given(bookRepository).deleteById(bookId);
    assertThatThrownBy(() -> bookService.delete(bookId)).isInstanceOf(IllegalArgumentException.class);
    verify(bookRepository, times(1)).deleteById(bookId);
  }

}
