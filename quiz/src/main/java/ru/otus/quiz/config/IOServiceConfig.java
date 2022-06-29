package ru.otus.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.quiz.service.IOService;
import ru.otus.quiz.service.IOServiceImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class IOServiceConfig {
  @Bean
  public IOService ioService() {
    return new IOServiceImpl(System.in, System.out);
  }

}
