package ru.otus.library.service.processors;

public interface AuthorCommandProcessor {

  String findById(long id);

  String findAll();

  String insert(String name);

  String update(long id, String name);

  String delete(long id);
  
}
