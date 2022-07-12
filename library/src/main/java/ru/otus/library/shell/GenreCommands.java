package ru.otus.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.AllArgsConstructor;
import ru.otus.library.domain.Genre;
import ru.otus.library.service.GenreService;
import ru.otus.library.service.converters.GenreConverter;

@AllArgsConstructor
@ShellComponent
public class GenreCommands {

  private final GenreService genreService;
  private final GenreConverter converter;

  @ShellMethod(value = "Create a genre", key = { "genre:create", "genre:insert" })
  String insert(@ShellOption String name) {
    genreService.insert(new Genre(null, name));
    return "Created";
  }

  @ShellMethod(value = "Find genre by id", key = { "genre:find", "genre:findById" })
  String findById(@ShellOption Long id) {
    return genreService.findById(id).map((genre) -> converter.convert(genre))
        .orElse("Genre with id=" + id + " not found");
  }

  @ShellMethod(value = "List all genres", key = { "genre:all", "genre:list", "genre:findAll" })
  String findAll() {
    return converter.convert(genreService.findAll());
  }

  @ShellMethod(value = "Update a genre", key = { "genre:update" })
  String update(@ShellOption Long id, @ShellOption String title) {
    var isUpdated = genreService.update(new Genre(id, title));
    return isUpdated ? "Updated" : "Couldn't update a genre with id=" + id;
  }

  @ShellMethod(value = "Delete a genre", key = { "genre:delete", "genre:del", "genre:remove" })
  String update(@ShellOption Long id) {
    var isDelted = genreService.delete(id);
    return isDelted ? "Deleted" : "Couldn't delete a genre with id=" + id;
  }

}
