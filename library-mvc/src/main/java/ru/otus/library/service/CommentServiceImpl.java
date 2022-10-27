package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Comment;
import ru.otus.library.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Transactional(readOnly = true)
  @Override
  public Optional<Comment> findById(Long id) {
    return commentRepository.findById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Comment> findByBookId(Long bookId) {
    return commentRepository.findByBookId(bookId);
  }

  @Transactional
  @Override
  public Comment save(Comment comment) {
    return commentRepository.save(comment);
  }

  @Transactional
  @Override
  public void delete(Long id) {
    commentRepository.deleteById(id);
  }

}
