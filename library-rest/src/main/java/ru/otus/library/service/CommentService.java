package ru.otus.library.service;

import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

  Optional<Comment> findById(Long id);

  List<Comment> findByBookId(Long bookId);

  Comment save(Comment comment);

  void delete(Long id);

}
