package ru.otus.library.service;

import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

  Optional<Comment> findById(String id);

  List<Comment> findByBookId(String bookId);

  Comment save(Comment comment);

  void delete(String id);

}
