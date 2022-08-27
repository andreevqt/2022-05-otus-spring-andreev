package ru.otus.library.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Comment;
import ru.otus.library.dto.CommentRequestDto;
import ru.otus.library.dto.CommentResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CommentMapperImpl implements CommentMapper {

  @Override
  public CommentResponseDto toDto(Comment comment) {
    return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getBook());
  }

  @Override
  public Comment fromDto(CommentRequestDto commentRequestDto) {
    return new Comment(null, null, commentRequestDto.getContent());
  }

  @Override
  public List<CommentResponseDto> toDtos(List<Comment> comments) {
    return comments.stream().map(this::toDto).collect(Collectors.toList());
  }

}
