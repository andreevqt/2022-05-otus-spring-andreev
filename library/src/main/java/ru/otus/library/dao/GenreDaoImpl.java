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

    return em.merge(genre);
  }

  @Override
  public void delete(Long id) {
    var query = em.createQuery("delete from Genre g where g.id = :id");
    query.setParameter("id", id);
    query.executeUpdate();
  }

}
