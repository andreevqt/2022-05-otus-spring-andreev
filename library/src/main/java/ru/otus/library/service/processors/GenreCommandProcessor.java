package ru.otus.library.service.processors;

public interface GenreCommandProcessor {

  String findById(long id);

  String findAll();

  String insert(String title);

  String update(long id, String title);

  String delete(long id);
  
}
