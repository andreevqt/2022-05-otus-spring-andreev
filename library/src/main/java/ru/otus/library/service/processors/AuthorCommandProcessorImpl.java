package ru.otus.library.service.processors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.otus.library.domain.Author;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.converters.AuthorConverter;

@Service
@AllArgsConstructor
public class AuthorCommandProcessorImpl implements AuthorCommandProcessor {

  private final AuthorService authorService;
  private final AuthorConverter converter;

  @Override
  public String findById(long id) {
    return authorService.findById(id).map(converter::convert)
      .orElse("Author with id=" + id + " not found");
  }

  @Override
  public String findAll() {
    return converter.convert(authorService.findAll());
  }

  @Override
  public String insert(String name) {
    authorService.save(new Author(null, name));
    return "Created";
  }

  @Override
  public String update(long id, String name) {
    authorService.save(new Author(id, name));
    return "Updated";
  }

  @Override
  public String delete(long id) {
    try {
      authorService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't delete an author with id=" + id;
    }
  }
  
}
