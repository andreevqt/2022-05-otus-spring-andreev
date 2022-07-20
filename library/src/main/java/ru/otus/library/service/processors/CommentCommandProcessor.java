package ru.otus.library.service.processors;

public interface CommentCommandProcessor {

  String findById(long id);

  String findByBookId(long bookId);

  String insert(long bookId, String content);

  String update(long id, long bookId, String content);

  String delete(Long id);
  
}
