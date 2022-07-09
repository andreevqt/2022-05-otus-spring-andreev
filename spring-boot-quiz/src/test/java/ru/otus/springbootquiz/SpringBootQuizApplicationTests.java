package ru.otus.springbootquiz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
class SpringBootQuizApplicationTests {

	@Test
	void contextLoads() {
	}

}
