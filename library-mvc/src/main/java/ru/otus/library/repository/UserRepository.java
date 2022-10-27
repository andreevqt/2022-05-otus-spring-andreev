package ru.otus.library.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.otus.library.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

  void deleteByUsername(String username);

}
