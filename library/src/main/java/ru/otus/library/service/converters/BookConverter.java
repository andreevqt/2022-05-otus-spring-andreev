package ru.otus.library.service.converters;

import ru.otus.library.domain.Book;

import java.util.List;

public interface BookConverter {

  String convert(Book book);

  String convert(List<Book> book);

}
