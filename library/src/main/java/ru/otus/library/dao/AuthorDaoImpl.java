package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

  @PersistenceContext
  private final EntityManager em;

  @Override
  public List<Author> findAll() {
    return em.createQuery("select a from Author a", Author.class).getResultList();
  }

  @Override
  public Optional<Author> findById(Long id) {
    return Optional.ofNullable(em.find(Author.class, id));
  }

  @Override
  public Author save(Author author) {
    if (author.getId() == null) {
      em.persist(author);
      return author;
    }

    return em.merge(author);
  }

  @Override
  public void delete(Long id) {
    em.remove(em.find(Author.class, id));
  }

}
