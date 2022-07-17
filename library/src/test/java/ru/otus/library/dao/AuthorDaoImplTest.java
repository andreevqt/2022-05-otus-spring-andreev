package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {

  @Autowired
  private AuthorDao authorDao;

  @DisplayName("возвращать список авторов")
  @Test
  void shouldReturnListOfAuthors() {
    var expected = List.of(
      new Author(1L, "Lewis Carroll"),
      new Author(2L, "J. K. Rowling"),
      new Author(3L, "Jane Austen")
    );
    assertThat(authorDao.findAll()).isEqualTo(expected);
  }

  @DisplayName("создавать автора")
  @Test
  void shouldCreateAuthor() {
    var author = new Author(4L, "Some author");

    authorDao.insert(author);

    assertThat(authorDao.findById(author.getId())).isEqualTo(Optional.of(author));
  }

  @DisplayName("возвращать автора по id")
  @Test
  void shouldReturnAuthorById() {
    var author = new Author(1L, "Lewis Carroll");
    assertThat(authorDao.findById(author.getId())).isEqualTo(Optional.of(author));
  }

  @DisplayName("возвращать пустой Optional если автор не найден")
  @Test
  void shouldReturnEmptyOptionalIfAuthorNotFound() {
    assertThat(authorDao.findById(44L).isEmpty()).isEqualTo(true);
  }

  @DisplayName("обновлять автора")
  @Test
  void shouldUpdateAuthor() {
    var author = new Author(1L, "Updated author");

    assertThat(authorDao.update(author)).isEqualTo(true);
    assertThat(authorDao.findById(author.getId())).isEqualTo(Optional.of(author));
  }

  @DisplayName("возвращать false если не получилось обновить")
  @Test
  void shouldReturnFalseIfCouldntUpdateAuthor() {
    var author = new Author(33L, "Some author");

    assertThat(authorDao.update(author)).isEqualTo(false);
  }

  @DisplayName("удалять автора по id")
  @Test
  void shouldDeleteAuthorById() {
    var authorId = 1L;
    assertThat(authorDao.delete(authorId)).isEqualTo(true);
    assertThat(authorDao.findById(authorId).isEmpty()).isEqualTo(true);
  }

  @DisplayName("возвращать false если не получилось удалить автора")
  @Test
  void shouldReturnFalseIfCouldntDeleteAuthor() {
    assertThat(authorDao.delete(100L)).isEqualTo(false);
  }

}
