package ru.otus.springbootquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.springbootquiz.service.App;

@SpringBootApplication
public class SpringBootQuizApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SpringBootQuizApplication.class, args);
		var app = context.getBean(App.class);
		app.run();
	}

}
