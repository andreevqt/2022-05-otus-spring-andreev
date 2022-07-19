package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с комментариями должно")
@DataJpaTest
@Import(CommentDaoImpl.class)
public class CommentDaoImplTest {

  @Autowired
  private CommentDao commentDao;

  @DisplayName("возвращать список комментариев")
  @Test
  void shouldReturnListOfComments() {
//    var book = new Book(
//      1L, "Alice's Adventures in Wonderland",
//      new Author(1L, "Lewis Carroll"), new Genre(1L, "Adventures")
//    );

    var expected = List.of(
      new Comment(1L, null, "First comment"),
      new Comment(2L, null, "Second comment"),
      new Comment(3L, null, "Third comment")
    );

    assertThat(commentDao.findAll()).usingRecursiveComparison().comparingOnlyFields("id", "content")
      .isEqualTo(expected);
  }

  @DisplayName("создавать комментарий")
  @Test
  void shouldCreateComment() {
    var comment = new Comment(null, null, "Some comment");
    commentDao.save(comment);
    assertThat(commentDao.findById(comment.getId())).isEqualTo(Optional.of(comment));
  }

  @DisplayName("возвращать комментарий по id")
  @Test
  void shouldReturnCommentById() {
    var comment = new Comment(1L, null, "First comment");
    var res = commentDao.findById(comment.getId());
    assertThat(res).isNotEmpty();
    assertThat(res.get()).usingRecursiveComparison().ignoringFields("book").isEqualTo(comment);
  }

  @DisplayName("возвращать пустой Optional если комментарий не найден")
  @Test
  void shouldReturnEmptyOptionalIfCommentNotFound() {
    assertThat(commentDao.findById(44L)).isEmpty();
  }

  @DisplayName("обновлять комментарий")
  @Test
  void shouldUpdateComment() {
    var comment = new Comment(1L, null, "Updated comment");
    commentDao.save(comment);
    assertThat(commentDao.findById(comment.getId())).usingRecursiveComparison().isEqualTo(Optional.of(comment));
  }

  @DisplayName("удалять комментарий по id")
  @Test
  void shouldDeleteAuthorById() {
    var commentId = 1L;
    commentDao.delete(commentId);
    assertThat(commentDao.findById(commentId).isEmpty()).isEqualTo(true);
  }

  @DisplayName("бросать исключение если не получилось удалить комментарий")
  @Test
  void shouldThrowIfCouldntDeleteComment() {
    assertThatThrownBy(() -> commentDao.delete(100L)).isInstanceOf(IllegalArgumentException.class);
  }

}
