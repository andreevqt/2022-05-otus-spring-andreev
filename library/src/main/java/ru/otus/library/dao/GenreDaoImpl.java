package ru.otus.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import ru.otus.library.domain.Genre;

@Repository
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao {

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;
  private final RowMapper<Genre> mapper = new GenreMapper();

  @Override
  public List<Genre> findAll() {
    return namedParameterJdbcOperations.query("select id, title from genres", mapper);
  }

  @Override
  public Optional<Genre> findById(long id) {
    try {
      return Optional.of(namedParameterJdbcOperations.queryForObject("select id, title from genres where id = :id",
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
  public boolean delete(long id) {
    return namedParameterJdbcOperations.update("delete from genres where id = :id", Map.of("id", id)) > 0;
  }

  private static class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet result, int i) throws SQLException {
      var id = result.getLong("id");
      var title = result.getString("title");
      return new Genre(id, title);
    }

  }

}
