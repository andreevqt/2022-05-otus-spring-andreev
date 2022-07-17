package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Genre;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao {

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  private final RowMapper<Genre> mapper = (ResultSet result, int i) -> {
    var id = result.getLong("id");
    var title = result.getString("title");
    return new Genre(id, title);
  };

  @Override
  public List<Genre> findAll() {
    return namedParameterJdbcOperations.query("select id, title from genres", mapper);
  }

  @Override
  public Optional<Genre> findById(Long id) {
    try {
      return Optional.ofNullable(namedParameterJdbcOperations.queryForObject("select id, title from genres where id = :id",
        Map.of("id", id), mapper));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public void insert(Genre genre) {
    namedParameterJdbcOperations.update("insert into genres (title) values(:title)", Map.of("title", genre.getTitle()));
  }

  @Override
  public boolean update(Genre genre) {
    return namedParameterJdbcOperations.update("update genres set title = :title where id = :id",
      Map.of("id", genre.getId(), "title", genre.getTitle())) > 0;
  }

  @Override
  public boolean delete(Long id) {
    return namedParameterJdbcOperations.update("delete from genres where id = :id", Map.of("id", id)) > 0;
  }

}
