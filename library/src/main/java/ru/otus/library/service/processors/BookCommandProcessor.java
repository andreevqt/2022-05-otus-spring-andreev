package ru.otus.library.service.processors;

public interface BookCommandProcessor {

  String findById(long id);

  String findAll();

  String insert(String title, long genreId, long authorId);

  String update(long id, String title, long genreId, long authorId);

  String delete(long id);
  
}
