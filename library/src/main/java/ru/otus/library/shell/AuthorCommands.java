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
    authorService.insert(new Author(null, name));
    return "Created";
  }

  @ShellMethod(value = "Find author by id", key = {"author:find", "author:findById"})
  String findById(@ShellOption Long id) {
    return authorService.findById(id).map((author) -> converter.convert(author))
      .orElse("Author with id=" + id + " not found");
  }

  @ShellMethod(value = "List all authors", key = {"author:all", "author:list", "author:findAll"})
  String findAll() {
    return converter.convert(authorService.findAll());
  }

  @ShellMethod(value = "Update an author", key = {"author:update"})
  String update(@ShellOption Long id, @ShellOption String name) {
    var isUpdated = authorService.update(new Author(id, name));
    return isUpdated ? "Updated" : "Couldn't update an author with id=" + id;
  }

  @ShellMethod(value = "Delete an author", key = {"author:delete", "author:del", "author:remove"})
  String update(@ShellOption Long id) {
    var isDelted = authorService.delete(id);
    return isDelted ? "Deleted" : "Couldn't delete an author with id=" + id;
  }

}
