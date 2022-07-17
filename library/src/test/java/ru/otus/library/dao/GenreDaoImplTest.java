package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {

  @Autowired
  private GenreDao genreDao;

  @DisplayName("возвращать список авторов")
  @Test
  void shouldReturnListOfGenres() {
    var expected = List.of(
      new Genre(1L, "Adventures"),
      new Genre(2L, "Novels")
    );
    assertThat(genreDao.findAll()).isEqualTo(expected);
  }

  @DisplayName("создавать жанр")
  @Test
  void shouldCreateGenre() {
    var genre = new Genre(3L, "Some genre");

    genreDao.insert(genre);

    assertThat(genreDao.findById(genre.getId())).isEqualTo(Optional.of(genre));
  }

  @DisplayName("возвращать жанр по id")
  @Test
  void shouldReturnGenreById() {
    var genre = new Genre(1L, "Adventures");
    assertThat(genreDao.findById(genre.getId())).isEqualTo(Optional.of(genre));
  }

  @DisplayName("возвращать пустой Optional если жанр не найден")
  @Test
  void shouldReturnEmptyOptionalIfGenreNotFound() {
    assertThat(genreDao.findById(44L).isEmpty()).isEqualTo(true);
  }

  @DisplayName("обновлять жанр")
  @Test
  void shouldUpdateGenre() {
    var genre = new Genre(1L, "Updated genre");

    assertThat(genreDao.update(genre)).isEqualTo(true);
    assertThat(genreDao.findById(genre.getId())).isEqualTo(Optional.of(genre));
  }

  @DisplayName("возвращать false если не получилось обновить жанр")
  @Test
  void shouldReturnFalseIfCouldntUpdateGenre() {
    var genre = new Genre(33L, "Some genre");

    assertThat(genreDao.update(genre)).isEqualTo(false);
  }

  @DisplayName("удалять жанр по id")
  @Test
  void shouldDeleteGenreById() {
    var genreId = 1L;
    assertThat(genreDao.delete(genreId)).isEqualTo(true);
    assertThat(genreDao.findById(genreId).isEmpty()).isEqualTo(true);
  }

  @DisplayName("возвращать false если не получилось удалить жанр")
  @Test
  void shouldReturnFalseIfCouldntDeleteGenre() {
    assertThat(genreDao.delete(100L)).isEqualTo(false);
  }

}
