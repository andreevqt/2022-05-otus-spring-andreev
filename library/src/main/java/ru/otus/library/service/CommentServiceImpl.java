package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

  @PersistenceContext
  private final EntityManager em;

  @Transactional(readOnly = true)
  @Override
  public Optional<Comment> findById(Long id) {
    return Optional.ofNullable(em.find(Comment.class, id));
  }

  @Transactional
  @Override
  public Comment save(Comment comment) {
    if (comment.getId() == null) {
      em.persist(comment);
      return comment;
    }

    return em.merge(comment);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Comment> findAll() {
    return em.createQuery("select c from Comment c", Comment.class).getResultList();
  }

  @Transactional
  @Override
  public void delete(Long id) {
    var query = em.createQuery("delete from Comment c where c.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }
}
