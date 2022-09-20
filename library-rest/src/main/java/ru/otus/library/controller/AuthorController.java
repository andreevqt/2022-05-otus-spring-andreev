package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.dto.AuthorRequestDto;
import ru.otus.library.exceptions.ResourceNotFoundException;
import ru.otus.library.mappers.AuthorMapper;
import ru.otus.library.service.AuthorService;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@RestController
public class AuthorController {

  private final AuthorService authorService;
  private final AuthorMapper authorMapper;

  @GetMapping("/authors")
  public ResponseEntity<?> list() {
    var items = authorMapper.toDtos(authorService.findAll());
    return ResponseEntity.ok(Map.of("success", true, "items", items));
  }

  @PostMapping("/authors")
  public ResponseEntity<?> create(@Valid @RequestBody AuthorRequestDto dto) {
    var author = authorService.save(authorMapper.fromDto(dto));
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
      "success", true,
      "author", author
    ));
  }

  @PatchMapping("/authors/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody AuthorRequestDto dto) {
    return authorService.findById(id).map((item) -> {
      var mappedAuthor = authorMapper.fromDto(dto);
      mappedAuthor.setId(id);
      return ResponseEntity.ok(Map.of(
        "success", true,
        "author", authorService.save(mappedAuthor))
      );
    }).orElseThrow(ResourceNotFoundException::new);
  }

  @GetMapping("/authors/{id}")
  public ResponseEntity<?> get(@PathVariable("id") Long id) {
    return authorService.findById(id)
      .map((author) -> ResponseEntity.ok(Map.of("success", true, "author", author)))
      .orElseThrow(ResourceNotFoundException::new);
  }

  @DeleteMapping("/authors/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      authorService.delete(id);
      return ResponseEntity.ok(Map.of("success", true));
    } catch (Exception e) {
      throw new ResourceNotFoundException();
    }
  }

}
