package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class CommentDaoImpl implements CommentDao {

  @PersistenceContext
  private final EntityManager em;

  @Override
  public List<Comment> findAll() {
    return em.createQuery("select c from Comment c left join Book b", Comment.class).getResultList();
  }

  @Override
  public Optional<Comment> findById(Long id) {
    return Optional.ofNullable(em.find(Comment.class, id));
  }

  @Override
  public Comment save(Comment comment) {
    if (comment.getId() == null) {
      em.persist(comment);
      return comment;
    }

    return em.merge(comment);
  }

  @Override
  public void delete(Long id) {
    var query = em.createQuery("delete from Comment c where c.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }

}
