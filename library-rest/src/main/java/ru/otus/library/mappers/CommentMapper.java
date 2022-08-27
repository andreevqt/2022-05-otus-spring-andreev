package ru.otus.library.mappers;

import ru.otus.library.domain.Comment;
import ru.otus.library.dto.CommentRequestDto;
import ru.otus.library.dto.CommentResponseDto;

import java.util.List;

public interface CommentMapper {

  CommentResponseDto toDto(Comment comment);

  Comment fromDto(CommentRequestDto commentRequestDto);

  List<CommentResponseDto> toDtos(List<Comment> comments);

}
