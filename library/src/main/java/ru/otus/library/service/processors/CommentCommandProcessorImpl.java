package ru.otus.library.service.processors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
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
  public String findById(long id) {
    return commentService.findById(id).map(converter::convert)
      .orElse("Comment with id=" + id + " not found");
  }

  @Transactional(readOnly = true)
  @Override
  public String findByBookId(long bookId) {
    return converter.convert(commentService.findByBookId(bookId));
  }

  @Override
  public String insert(long bookId, String content) {
    var book = bookService.findById(bookId);
    commentService.save(new Comment(null, book.orElse(null), content));
    return "Created";
  }

  @Override
  public String delete(Long id) {
    try {
      commentService.delete(id);
      return "Deleted";
    } catch (Exception e) {
      return "Couldn't delete a comment with id=" + id;
    }
  }

  @Override
  public String update(long id, long bookId, String content) {
    var book = bookService.findById(bookId);
    commentService.save(new Comment(id, book.orElse(null), content));
    return "Updated";
  }

}
