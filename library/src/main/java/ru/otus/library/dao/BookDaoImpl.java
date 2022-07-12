package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;
  private final RowMapper<Book> mapper = new BookMapper();

  @Override
  public Optional<Book> findById(long id) {
    try {
      return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(
        "select id, title, author_id, genre_id from books where id = :id", Map.of("id", id), mapper));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public void insert(Book book) {
    var attrs = new HashMap<String, Object>();

    attrs.put("title", book.getTitle());
    attrs.put("authorId", book.getAuthorId());
    attrs.put("genreId", book.getGenreId());

    namedParameterJdbcOperations
      .update("insert into books (title, author_id, genre_id) values (:title, :authorId, :genreId)", attrs);
  }

  @Override
  public List<Book> findAll() {
    return namedParameterJdbcOperations.query("select id, title, author_id, genre_id from books", mapper);
  }

  @Override
  public boolean update(Book book) {
    var attrs = new HashMap<String, Object>();

    attrs.put("id", book.getId());
    attrs.put("title", book.getTitle());
    attrs.put("authorId", book.getAuthorId());
    attrs.put("genreId", book.getGenreId());

    return namedParameterJdbcOperations.update(
      "update books set title = :title, author_id = :authorId, genre_id = :genreId where id = :id", attrs) > 0;
  }

  @Override
  public boolean delete(long id) {
    return namedParameterJdbcOperations.update("delete from books where id = :id", Map.of("id", id)) > 0;
  }

  private static class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
      Long id = resultSet.getLong("id");
      var title = resultSet.getString("title");
      Long genreId = resultSet.getLong("genre_id");
      Long authorId = resultSet.getLong("author_id");

      return new Book(id, title, genreId, authorId);
    }

  }

}
