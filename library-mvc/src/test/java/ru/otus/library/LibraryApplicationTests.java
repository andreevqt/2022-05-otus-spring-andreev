package ru.otus.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class LibraryApplicationTests {

  @Test
  void contextLoads() {
  }

}
