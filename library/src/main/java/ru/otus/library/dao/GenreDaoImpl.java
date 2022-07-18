package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao {

  @PersistenceContext
  private final EntityManager em;

  @Override
  public List<Genre> findAll() {
    return em.createQuery("select g from Genre g", Genre.class).getResultList();
  }

  @Override
  public Optional<Genre> findById(Long id) {
    return Optional.ofNullable(em.find(Genre.class, id));
  }

  @Override
  public Genre save(Genre genre) {
    if (genre.getId() == null) {
      em.persist(genre);
      return genre;
    }

    if (!isExists(genre.getId())) {
      throw new IllegalArgumentException("Genre with id=" + genre.getId() + " not exists");
    }

    return em.merge(genre);
  }

  @Override
  public void delete(Long id) {
    em.remove(em.find(Genre.class, id));
  }

  private boolean isExists(Long id) {
    return em.createQuery("select count(g) from Genre g where id = :id", Long.class)
      .setParameter("id", id)
      .getSingleResult() > 0;
  }

}
