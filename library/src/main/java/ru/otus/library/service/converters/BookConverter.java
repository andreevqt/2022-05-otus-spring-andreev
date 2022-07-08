package ru.otus.library.service.converters;

import java.util.List;

import ru.otus.library.domain.Book;

public interface BookConverter {

  String convert(Book book);
  
  String convert(List<Book> book);
  
}
