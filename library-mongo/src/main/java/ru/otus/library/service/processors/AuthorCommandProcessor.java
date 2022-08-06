package ru.otus.library.service.processors;

public interface AuthorCommandProcessor {

  String findById(String id);

  String findAll();

  String insert(String name);

  String update(String id, String name);

  String delete(String id);
  
}
