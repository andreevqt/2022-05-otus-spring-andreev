package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.Author;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.converters.AuthorConverter;

@AllArgsConstructor
@ShellComponent
public class AuthorCommands {

  private final AuthorService authorService;
  private final AuthorConverter converter;

  @ShellMethod(value = "Create author", key = {"author:create", "author:insert"})
  String insert(@ShellOption String name) {
    authorService.save(new Author(null, name));
    return "Created";
  }

  @ShellMethod(value = "Find author by id", key = {"author:find", "author:findById"})
  String findById(@ShellOption long id) {
    return authorService.findById(id).map(converter::convert)
      .orElse("Author with id=" + id + " not found");
  }

  @ShellMethod(value = "List all authors", key = {"author:all", "author:list", "author:findAll"})
  String findAll() {
    return converter.convert(authorService.findAll());
  }

  @ShellMethod(value = "Update an author", key = {"author:update"})
  String update(@ShellOption long id, @ShellOption String name) {
    authorService.save(new Author(id, name));
    return "Updated";
  }

  @ShellMethod(value = "Delete an author", key = {"author:delete", "author:del", "author:remove"})
  String delete(@ShellOption long id) {
    try {
      authorService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't delete an author with id=" + id;
    }
  }

}
