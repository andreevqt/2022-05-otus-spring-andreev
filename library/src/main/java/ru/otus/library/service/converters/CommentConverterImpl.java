package ru.otus.library.service.converters;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentConverterImpl implements CommentConverter {

  @Override
  public String convert(Comment comment) {
    var book = comment.getBook();
    return "Comment(id=" + comment.getId() + ", book=" + (book != null ? book.getTitle() : "null") + ", content=" + comment.getContent() + ")";
  }

  @Override
  public String convert(List<Comment> comments) {
    return comments.stream().map(this::convert).collect(Collectors.joining("\n"));
  }

}
