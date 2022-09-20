package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.dto.BookRequestDto;
import ru.otus.library.exceptions.ResourceNotFoundException;
import ru.otus.library.mappers.BookMapper;
import ru.otus.library.service.BookService;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@RestController
public class BookController {

  private final BookService bookService;
  private final BookMapper bookMapper;

  @GetMapping("/books")
  public ResponseEntity<?> list() {
    return ResponseEntity.ok(Map.of(
      "success", true,
      "items", bookMapper.toDtos(bookService.findAll())
    ));
  }

  @PostMapping("/books")
  public ResponseEntity<?> create(@Valid @RequestBody BookRequestDto book) {
    var result = bookService.save(bookMapper.fromDto(null ,book));
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
      "success", true,
      "book", bookMapper.toDto(result)
    ));
  }

  @PatchMapping("/books/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody BookRequestDto dto) {
    return bookService.findById(id)
      .map((item) -> {
        var result = bookService.save(bookMapper.fromDto(item.getId(), dto));
        return ResponseEntity.ok(Map.of(
          "success", true,
          "book", bookMapper.toDto(result)
        ));
      }).orElseThrow(ResourceNotFoundException::new);
  }

  @GetMapping("/books/{id}")
  public ResponseEntity<?> get(@PathVariable("id") Long id) {
    return bookService.findById(id)
      .map((book) -> ResponseEntity.ok(Map.of(
        "success", true,
        "book", bookMapper.toDto(book)
      ))).orElseThrow(ResourceNotFoundException::new);
  }

  @DeleteMapping( "/books/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      bookService.delete(id);
      return ResponseEntity.ok(Map.of("success", true));
    } catch (Exception e) {
      throw new ResourceNotFoundException();
    }
  }
}
