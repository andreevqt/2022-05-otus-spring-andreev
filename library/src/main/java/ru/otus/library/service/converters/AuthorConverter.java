package ru.otus.library.service.converters;

import java.util.List;

import ru.otus.library.domain.Author;

public interface AuthorConverter {

  String convert(Author author);

  String convert(List<Author> authors);

}
