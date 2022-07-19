package ru.otus.library.dao;

import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

  Optional<Comment> findById(Long id);

  List<Comment> findByBookId(Long bookId);

  Comment save(Comment comment);

  void delete(Long id);

}
