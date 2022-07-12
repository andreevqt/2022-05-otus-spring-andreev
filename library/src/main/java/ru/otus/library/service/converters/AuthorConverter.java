package ru.otus.library.service.converters;

import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorConverter {

  String convert(Author author);

  String convert(List<Author> authors);

}
