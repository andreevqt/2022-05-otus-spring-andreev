package ru.otus.springbootquiz.service;

public interface IOService {

  void out(String str);

  int readInt();

  int readIntWithPrompt(String prompt);
  
  String readStringWithPrompt(String prompt);

}
