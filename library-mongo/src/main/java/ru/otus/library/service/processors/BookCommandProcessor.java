package ru.otus.library.service.processors;

public interface BookCommandProcessor {

  String findById(String id);

  String findAll();

  String insert(String title, String genre, String authorId);

  String update(String id, String title, String genreId, String authorId);

  String delete(String id);
  
}
