package ru.otus.library.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с книгами должно")
@DataJpaTest
class BookDaoTest {

  private static final long EXPECTED_QUERIES_COUNT = 1;

  @Autowired
  private BookDao dao;
  @Autowired
  private TestEntityManager em;

  @DisplayName("должно генерировать ожидаемое количество запросов")
  @Test
  void shouldGenerateExpectedQueriesCount() {
    var sessionFactory = em.getEntityManager().getEntityManagerFactory()
      .unwrap(SessionFactory.class);
    sessionFactory.getStatistics().clear();
    sessionFactory.getStatistics().setStatisticsEnabled(true);
    dao.findAll();
    assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
  }

}
