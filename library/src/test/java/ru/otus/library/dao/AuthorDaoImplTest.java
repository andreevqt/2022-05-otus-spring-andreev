package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с авторами должно")
@DataJpaTest
@Import(AuthorDao.class)
class AuthorDaoImplTest {

  @Autowired
  private AuthorDao authorDao;

  @DisplayName("возвращать список авторов")
  @Test
  void shouldReturnListOfAuthors() {
    var expected = List.of(
        new Author(1L, "Lewis Carroll"),
        new Author(2L, "J. K. Rowling"),
        new Author(3L, "Jane Austen"));
    assertThat(authorDao.findAll()).usingRecursiveComparison().isEqualTo(expected);
  }

  @DisplayName("создавать автора")
  @Test
  void shouldCreateAuthor() {
    var author = new Author(null, "Some author");
    authorDao.save(author);
    assertThat(authorDao.findById(author.getId())).isEqualTo(Optional.of(author));
  }

  @DisplayName("возвращать автора по id")
  @Test
  void shouldReturnAuthorById() {
    var author = new Author(1L, "Lewis Carroll");
    assertThat(authorDao.findById(author.getId())).usingRecursiveComparison().isEqualTo(Optional.of(author));
  }

  @DisplayName("возвращать пустой Optional если автор не найден")
  @Test
  void shouldReturnEmptyOptionalIfAuthorNotFound() {
    assertThat(authorDao.findById(44L)).isEmpty();
  }

  @DisplayName("обновлять автора")
  @Test
  void shouldUpdateAuthor() {
    var author = new Author(1L, "Updated author");
    authorDao.save(author);

    assertThat(authorDao.findById(author.getId())).usingRecursiveComparison().isEqualTo(Optional.of(author));
  }

  @DisplayName("удалять автора по id")
  @Test
  void shouldDeleteAuthorById() {
    var authorId = 1L;
    authorDao.deleteById(authorId);
    assertThat(authorDao.findById(authorId).isEmpty()).isEqualTo(true);
  }

  @DisplayName("бросать исключение если не получилось удалить автора")
  @Test
  void shouldThrowIfCouldntDeleteAuthor() {
    assertThatThrownBy(() -> authorDao.deleteById(100L)).isInstanceOf(IllegalArgumentException.class);
  }

}
