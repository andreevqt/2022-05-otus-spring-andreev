package ru.otus.library.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с книгами должно")
@DataJpaTest
class BookRepositoryTest {

  private static final int EXPECTED_QUERIES_COUNT = 1;

  @Autowired
  private BookRepository repository;
  @Autowired
  private TestEntityManager em;

  @DisplayName("генерировать ожидаемое количество запросов")
  @Test
  void shouldGenerateExpectedQueriesCount() {
    var sessionFactory = em.getEntityManager().getEntityManagerFactory()
      .unwrap(SessionFactory.class);
    sessionFactory.getStatistics().clear();
    sessionFactory.getStatistics().setStatisticsEnabled(true);
    repository.findAll();
    assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
  }

}
