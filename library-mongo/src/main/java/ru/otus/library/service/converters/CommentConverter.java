package ru.otus.library.service.converters;

import ru.otus.library.domain.Comment;

import java.util.List;

public interface CommentConverter {

  String convert(Comment comment);

  String convert(List<Comment> comments);

}
