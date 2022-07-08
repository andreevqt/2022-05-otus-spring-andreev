package ru.otus.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import ru.otus.library.domain.Author;

@AllArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;
  private final RowMapper<Book> mapper = new BookMapper();

  @Override
  public Optional<Book> findById(long id) {
    try {
      return Optional.of(namedParameterJdbcOperations.queryForObject(
          "select books.id as id, books.title as title, authors.id as author_id, authors.name as author_name, genres.id as genre_id, genres.title as genre_title from books left join authors on authors.id = books.author_id left join genres on genres.id = books.genre_id where books.id = :id",
          Map.of("id", id), mapper));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public void insert(Book book) {
    var attrs = new HashMap<String, Object>();

    attrs.put("title", book.getTitle());
    attrs.put("genreId", book.getGenreId());

    namedParameterJdbcOperations.update("insert into books (title, genre_id) values (:title, :genreId)", attrs);
  }

  @Override
  public List<Book> findAll() {
    return namedParameterJdbcOperations.query(
        "select books.id as id, books.title as title, authors.id as author_id, authors.name as author_name, genres.id as genre_id, genres.title as genre_title from books left join authors on authors.id = books.author_id left join genres on genres.id = books.genre_id",
        mapper);
  }

  @Override
  public void update(Book book) {
    var attrs = new HashMap<String, Object>();

    attrs.put("id", book.getId());
    attrs.put("title", book.getTitle());
    attrs.put("genreId", book.getGenreId());
    attrs.put("authorId", book.getAuthorId());

    namedParameterJdbcOperations
        .update("update books set title = :title, genre_id = :genreId, author_id = :authorId where id = :id", attrs);
  }

  @Override
  public void delete(long id) {
    namedParameterJdbcOperations.update("delete from books where id = :id", Map.of("id", id));
  }

  private static class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
      Long id = resultSet.getLong("id");
      var title = resultSet.getString("title");

      Long genreId = resultSet.getLong("genre_id");
      var genreTitle = resultSet.getString("genre_title");

      Long authorId = resultSet.getLong("author_id");
      var authorName = resultSet.getString("author_name");

      var genre = genreId != 0 ? new Genre(genreId, genreTitle) : null;
      var author = authorId != 0 ? new Author(authorId, authorName) : null;

      return new Book(id, title, genreId, genre, authorId, author);
    }

  }

}
