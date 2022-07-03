package ru.otus.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.quiz.service.IOService;
import ru.otus.quiz.service.IOServiceImpl;

@Configuration
public class IOServiceConfig {

  @Bean
  public IOService ioService() {
    return new IOServiceImpl(System.in, System.out);
  }

}
