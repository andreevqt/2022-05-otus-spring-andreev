package ru.otus.library.service.processors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Comment;
import ru.otus.library.service.BookService;
import ru.otus.library.service.CommentService;
import ru.otus.library.service.converters.CommentConverter;

@Service
@AllArgsConstructor
public class CommentCommandProcessorImpl implements CommentCommandProcessor {

  private final CommentConverter converter;
  private final CommentService commentService;
  private final BookService bookService;

  @Transactional(readOnly = true)
  @Override
  public String findById(String id) {
    return commentService.findById(id).map(converter::convert)
      .orElse("Comment with id=" + id + " not found");
  }

  @Transactional(readOnly = true)
  @Override
  public String findByBookId(String bookId) {
    return converter.convert(commentService.findByBookId(bookId));
  }

  @Transactional
  @Override
  public String insert(String bookId, String content) {
    var book = bookService.findById(bookId);
    commentService.save(new Comment(null, book.orElse(null), content));
    return "Created";
  }

  @Override
  public String delete(String id) {
    try {
      commentService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't delete a comment with id=" + id;
    }
  }

  @Transactional
  @Override
  public String update(String id, String bookId, String content) {
    var book = bookService.findById(bookId);
    commentService.save(new Comment(id, book.orElse(null), content));
    return "Updated";
  }

}
