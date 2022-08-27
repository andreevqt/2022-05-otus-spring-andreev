package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.dto.GenreDto;
import ru.otus.library.exceptions.ResourceNotFoundException;
import ru.otus.library.mappers.GenreMapper;
import ru.otus.library.service.GenreService;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreController {

  private final GenreService genreService;
  private final GenreMapper genreMapper;

  @GetMapping("")
  public ResponseEntity<?> list() {
    var items = genreMapper.toDtos(genreService.findAll());
    return ResponseEntity.ok(Map.of(
      "success", true, "items", items
    ));
  }

  @PostMapping("")
  public ResponseEntity<?> create(@RequestBody GenreDto dto) {
    var genre = genreService.save(genreMapper.fromDto(dto));
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
      "success", true, "genre", genre
    ));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody GenreDto dto) {
    return genreService.findById(id).map((item) -> {
      var mappedGenre = genreMapper.fromDto(dto);
      mappedGenre.setId(id);
      return ResponseEntity.ok(Map.of(
        "success", true,
        "genre", genreService.save(mappedGenre)
      ));
    }).orElseThrow(ResourceNotFoundException::new);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> get(@PathVariable("id") Long id) {
    return genreService.findById(id)
      .map((genre) -> ResponseEntity.ok(Map.of(
        "success", true,
        "genre", genre
      ))).orElseThrow(ResourceNotFoundException::new);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      genreService.delete(id);
      return ResponseEntity.ok(Map.of("success", true));
    } catch (Exception e) {
      throw new ResourceNotFoundException();
    }
  }

}
