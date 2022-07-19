package ru.otus.library.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.Genre;
import ru.otus.library.service.GenreService;
import ru.otus.library.service.converters.GenreConverter;

@AllArgsConstructor
@ShellComponent
public class GenreCommands {

  private final GenreService genreService;
  private final GenreConverter converter;

  @ShellMethod(value = "Create a genre", key = {"genre:create", "genre:insert"})
  String insert(@ShellOption String name) {
    genreService.save(new Genre(null, name));
    return "Created";
  }

  @ShellMethod(value = "Find genre by id", key = {"genre:find", "genre:findById"})
  String findById(@ShellOption Long id) {
    return genreService.findById(id).map(converter::convert)
      .orElse("Genre with id=" + id + " not found");
  }

  @ShellMethod(value = "List all genres", key = {"genre:all", "genre:list", "genre:findAll"})
  String findAll() {
    return converter.convert(genreService.findAll());
  }

  @ShellMethod(value = "Update a genre", key = {"genre:update"})
  String update(@ShellOption long id, @ShellOption String title) {
    genreService.save(new Genre(id, title));
    return "Updated";
  }

  @ShellMethod(value = "Delete a genre", key = {"genre:delete", "genre:del", "genre:remove"})
  String delete(@ShellOption long id) {
    try {
      genreService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't delete a genre with id=" + id;
    }
  }

}
