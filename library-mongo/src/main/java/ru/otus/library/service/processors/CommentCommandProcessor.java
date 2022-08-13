package ru.otus.library.service.processors;

public interface CommentCommandProcessor {

  String findById(String id);

  String findByBookId(String bookId);

  String insert(String bookId, String content);

  String update(String id, String bookId, String content);

  String delete(String id);
  
}
