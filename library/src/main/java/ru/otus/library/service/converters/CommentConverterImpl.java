package ru.otus.library.service.converters;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentConverterImpl implements CommentConverter {

  @Override
  public String convert(Comment comment) {
    return "Comment(id=" + comment.getId() + ", content=" + comment.getContent() + ")";
  }

  @Override
  public String convert(List<Comment> comments) {
    return comments.stream().map(this::convert).collect(Collectors.joining("\n"));
  }

}
