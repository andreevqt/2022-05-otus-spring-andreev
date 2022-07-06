package ru.otus.springbootquiz.service;

public interface IOTranslated {

  void out(String key, Object ...args);

  int readIntWithPrompt(String key, Object ...args);

  String readStringWithPrompt(String key, Object ...args);

}
