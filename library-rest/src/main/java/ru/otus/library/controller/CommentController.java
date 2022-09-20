package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.dto.CommentRequestDto;
import ru.otus.library.exceptions.ResourceNotFoundException;
import ru.otus.library.mappers.CommentMapper;
import ru.otus.library.service.BookService;
import ru.otus.library.service.CommentService;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
public class CommentController {

  private final CommentService commentService;
  private final CommentMapper commentMapper;

  private final BookService bookService;

  @GetMapping("/comments/book/{bookId}")
  public ResponseEntity<?> listByBookId(@PathVariable("bookId") Long bookId) {
    return ResponseEntity.ok(Map.of(
      "success", true,
      "items", commentService.findById(bookId)
    ));
  }

  @GetMapping("/comments/{id}")
  public ResponseEntity<?> get(@PathVariable("id") Long id) {
    return commentService.findById(id).map((comment) -> ResponseEntity.ok(Map.of(
      "success", true,
      "comment", comment
    ))).orElseThrow(ResourceNotFoundException::new);
  }

  @PostMapping("/comments/book/{bookId}")
  public ResponseEntity<?> create(@PathVariable("bookId") Long bookId, @Valid @RequestBody CommentRequestDto dto) {
    return bookService.findById(bookId).map((book) -> {
      var result = commentService.save(commentMapper.fromDto(null, book, dto));
      return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
        "success", true,
        "comment", result
      ));
    }).orElseThrow(ResourceNotFoundException::new);
  }

  @PatchMapping("/comments/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody CommentRequestDto dto) {
    return commentService.findById(id).map((item) -> {
      var result = commentService.save(commentMapper.fromDto(id, item.getBook(), dto));
      return ResponseEntity.ok(Map.of(
        "success", true,
        "comment", result
      ));
    }).orElseThrow(ResourceNotFoundException::new);
  }

  @DeleteMapping("/comments/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      commentService.delete(id);
      return ResponseEntity.ok(Map.of("success", true));
    } catch (Exception e) {
      throw new ResourceNotFoundException();
    }
  }

}
