package ru.otus.springbootquiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.springbootquiz.service.IOService;
import ru.otus.springbootquiz.service.IOServiceImpl;

@Configuration
public class IOConfig {

  @Bean
  public IOService ioService() {
    return new IOServiceImpl(System.in, System.out);
  }

}
