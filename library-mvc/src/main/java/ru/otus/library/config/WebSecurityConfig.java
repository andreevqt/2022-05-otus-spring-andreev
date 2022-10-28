package ru.otus.library.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .authorizeRequests()
      .antMatchers("/login**", "/css/**/*", "/js/**/*").permitAll()
      .antMatchers("/**/edit/*", "/**/create", "/**/delete/*").hasRole("ADMIN")
      .antMatchers("/*").hasAnyRole("ADMIN", "GUEST")
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login")
      .failureUrl("/login-error")
      .usernameParameter("login")
      .passwordParameter("password");

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
