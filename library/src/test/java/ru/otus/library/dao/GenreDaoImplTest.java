package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с жанрами должно")
@DataJpaTest
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
    assertThat(genreDao.findAll()).usingRecursiveComparison().isEqualTo(expected);
  }

  @DisplayName("создавать жанр")
  @Test
  void shouldCreateGenre() {
    var genre = new Genre(null, "Some genre");
    genreDao.save(genre);
    assertThat(genreDao.findById(genre.getId())).isEqualTo(Optional.of(genre));
  }

  @DisplayName("возвращать жанр по id")
  @Test
  void shouldReturnGenreById() {
    var genre = new Genre(1L, "Adventures");
    assertThat(genreDao.findById(genre.getId())).usingRecursiveComparison().isEqualTo(Optional.of(genre));
  }

  @DisplayName("возвращать пустой Optional если жанр не найден")
  @Test
  void shouldReturnEmptyOptionalIfGenreNotFound() {
    assertThat(genreDao.findById(44L)).isEmpty();
  }

  @DisplayName("обновлять жанр")
  @Test
  void shouldUpdateGenre() {
    var genre = new Genre(1L, "Updated genre");
    genreDao.save(genre);

    assertThat(genreDao.findById(genre.getId())).usingRecursiveComparison().isEqualTo(Optional.of(genre));
  }

  @DisplayName("удалять жанр по id")
  @Test
  void shouldDeleteGenreById() {
    var genreId = 1L;
    genreDao.delete(genreId);
    assertThat(genreDao.findById(genreId)).isEmpty();
  }

  @DisplayName("бросать исключение если не получилось удалить жанр")
  @Test
  void shouldThrowIfCouldntDeleteGenre() {
    assertThatThrownBy(() -> genreDao.delete(100L)).isInstanceOf(IllegalArgumentException.class);
  }

}
