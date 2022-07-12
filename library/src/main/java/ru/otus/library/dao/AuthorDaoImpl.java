package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;
  private final RowMapper<Author> mapper = new AuthorMapper();

  @Override
  public List<Author> findAll() {
    return namedParameterJdbcOperations.query("select id, name from authors", mapper);
  }

  @Override
  public Optional<Author> findById(long id) {
    try {
      return Optional.of(namedParameterJdbcOperations.queryForObject("select id, name from authors where id = :id",
        Map.of("id", id), mapper));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public void insert(Author author) {
    namedParameterJdbcOperations.update("insert into authors (name) values(:name)", Map.of("name", author.getName()));
  }

  @Override
  public boolean update(Author author) {
    return namedParameterJdbcOperations.update("update authors set name = :name where id = :id",
      Map.of("id", author.getId(), "name", author.getName())) > 0;
  }

  @Override
  public boolean delete(long id) {
    return namedParameterJdbcOperations.update("delete from authors where id = :id", Map.of("id", id)) > 0;
  }

  private static class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet result, int i) throws SQLException {
      var id = result.getLong("id");
      var name = result.getString("name");
      return new Author(id, name);
    }

  }

}
