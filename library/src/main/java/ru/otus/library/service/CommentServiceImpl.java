package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.dao.CommentDao;
import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

  private final CommentDao commentDao;

  @Transactional(readOnly = true)
  @Override
  public Optional<Comment> findById(Long id) {
    return commentDao.findById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Comment> findByBookId(Long bookId) {
    return commentDao.findByBookId(bookId);
  }

  @Transactional
  @Override
  public Comment save(Comment comment) {
    return commentDao.save(comment);
  }

  @Transactional
  @Override
  public void delete(Long id) {
    commentDao.deleteById(id);
  }

}
