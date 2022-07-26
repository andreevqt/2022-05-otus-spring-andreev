package ru.otus.library.dao;

import ru.otus.library.domain.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface CommentDao extends CrudRepository<Comment, Long> {

  @EntityGraph(attributePaths = "book")
  List<Comment> findByBookId(Long bookId);

}
