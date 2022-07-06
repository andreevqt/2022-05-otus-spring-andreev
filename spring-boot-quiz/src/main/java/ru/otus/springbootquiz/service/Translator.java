package ru.otus.springbootquiz.service;

public interface Translator {

  String translate(String key, Object ...args);
  
}
