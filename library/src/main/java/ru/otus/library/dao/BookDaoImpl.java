package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {

  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  private final RowMapper<Book> mapper = (ResultSet resultSet, int i) -> {
    var id = resultSet.getLong("id");
    var title = resultSet.getString("title");

    var authorId = resultSet.getLong("author_id");
    var author = authorId != 0 ? new Author(authorId, resultSet.getString("author_name")) : null;

    var genreId = resultSet.getLong("genre_id");
    var genre = genreId != 0 ? new Genre(genreId, resultSet.getString("genre_title")) : null;

    return new Book(id, title, author, genre);
  };

  @Override
  public Optional<Book> findById(Long id) {
    try {
      return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(
        "select books.id as id, books.title as title, authors.id as author_id, authors.name as author_name, " +
          "genres.id as genre_id, genres.title as genre_title " +
          "from books " +
          "left join authors on books.author_id = authors.id " +
          "left join genres on books.genre_id = genres.id " +
          "where books.id = :id", Map.of("id", id), mapper));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public void insert(Book book) {
    var attrs = new HashMap<String, Object>();

    attrs.put("title", book.getTitle());

    var author = book.getAuthor();
    attrs.put("authorId", author != null ? author.getId() : null);

    var genre = book.getGenre();
    attrs.put("genreId", genre != null ? genre.getId() : null);

    namedParameterJdbcOperations
      .update("insert into books (title, author_id, genre_id) values (:title, :authorId, :genreId)", attrs);
  }

  @Override
  public List<Book> findAll() {
    return namedParameterJdbcOperations.query("select books.id as id, books.title as title, " +
      "authors.id as author_id, authors.name as author_name, " +
      "genres.id as genre_id, genres.title as genre_title " +
      "from books " +
      "left join authors on books.author_id = authors.id " +
      "left join genres on books.genre_id = genres.id ", mapper);
  }

  @Override
  public boolean update(Book book) {
    var attrs = new HashMap<String, Object>();

    attrs.put("id", book.getId());
    attrs.put("title", book.getTitle());

    var author = book.getAuthor();
    attrs.put("authorId", author != null ? author.getId() : null);

    var genre = book.getGenre();
    attrs.put("genreId", genre != null ? genre.getId() : null);

    return namedParameterJdbcOperations.update(
      "update books set title = :title, author_id = :authorId, genre_id = :genreId where id = :id", attrs) > 0;
  }

  @Override
  public boolean delete(Long id) {
    return namedParameterJdbcOperations.update("delete from books where id = :id", Map.of("id", id)) > 0;
  }

}
