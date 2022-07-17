package ru.otus.library.service;

import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

  Optional<Comment> findById(Long id);

  Comment save(Comment comment);

  List<Comment> findAll();

  void delete(Long id);

}
