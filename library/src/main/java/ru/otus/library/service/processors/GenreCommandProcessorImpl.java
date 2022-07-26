package ru.otus.library.service.processors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Genre;
import ru.otus.library.service.GenreService;
import ru.otus.library.service.converters.GenreConverter;

@Service
@AllArgsConstructor
public class GenreCommandProcessorImpl implements GenreCommandProcessor {

  private final GenreService genreService;
  private final GenreConverter converter;

  @Override
  public String findById(long id) {
    return genreService.findById(id).map(converter::convert)
      .orElse("Genre with id=" + id + " not found");
  }

  @Override
  public String findAll() {
    return converter.convert(genreService.findAll());
  }

  @Override
  public String insert(String title) {
    genreService.save(new Genre(null, title));
    return "Created";
  }

  @Override
  public String update(long id, String title) {
    genreService.save(new Genre(id, title));
    return "Updated";
  }

  @Override
  public String delete(long id) {
    try {
      genreService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't delete a genre with id=" + id;
    }
  }
  
}
