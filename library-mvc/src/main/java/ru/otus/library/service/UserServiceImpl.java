package ru.otus.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import ru.otus.library.repository.UserRepository;

public class UserServiceImpl implements UserDetailsManager {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .authorities("ADMIN")
        .build();
  }

  @Override
  public void createUser(UserDetails user) {
    var newUser = new ru.otus.library.domain.User();
    newUser.setUsername(user.getUsername());
    newUser.setPassword(user.getPassword());
    newUser.setEnabled(true);
    repository.save(newUser);
  }

  @Override
  public void updateUser(UserDetails user) {
    repository.findByUsername(user.getUsername()).ifPresent((model) -> {
      model.setUsername(user.getUsername());
      model.setPassword(user.getPassword());
    });
  }

  @Override
  public void deleteUser(String username) {
    repository.deleteByUsername(username);
  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean userExists(String username) {
    return repository.existsByUsername(username);
  }
}
