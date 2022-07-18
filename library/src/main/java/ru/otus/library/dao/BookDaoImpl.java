package ru.otus.library.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {

  @PersistenceContext
  private final EntityManager em;

  @Override
  public Optional<Book> findById(Long id) {
    return Optional.ofNullable(em.find(Book.class, id));
  }

  @Override
  public Book save(Book book) {
    if (book.getId() == null) {
      em.persist(book);
      return book;
    }

    if (!isExists(book.getId())) {
      throw new IllegalArgumentException("Book with id=" + book.getId() + " not exists");
    }

    return em.merge(book);
  }

  @Override
  public List<Book> findAll() {
    return em.createQuery("select distinct b " +
      "from Book b " +
      "left join fetch b.author " +
      "left join fetch b.genre " +
      "left join fetch b.comments", Book.class).getResultList();
  }

  @Override
  public void delete(Long id) {
    em.remove(em.find(Book.class, id));
  }

  private boolean isExists(Long id) {
    return em.createQuery("select count(b) from Book b where id = :id", Long.class)
      .setParameter("id", id)
      .getSingleResult() > 0;
  }

}
