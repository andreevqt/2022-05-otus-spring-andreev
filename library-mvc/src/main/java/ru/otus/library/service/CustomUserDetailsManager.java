package ru.otus.library.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.repository.UserRepository;

@AllArgsConstructor
@Service
public class CustomUserDetailsManager implements UserDetailsManager {

  private UserRepository repository;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
  }

  @Transactional
  @Override
  public void createUser(UserDetails user) {
    var newUser = new ru.otus.library.domain.User();
    newUser.setUsername(user.getUsername());
    newUser.setPassword(user.getPassword());
    newUser.setEnabled(true);
    repository.save(newUser);
  }

  @Transactional
  @Override
  public void updateUser(UserDetails user) {
    repository.findByUsername(user.getUsername()).ifPresent((model) -> {
      model.setUsername(user.getUsername());
      model.setPassword(user.getPassword());
      repository.save(model);
    });
  }

  @Transactional
  @Override
  public void deleteUser(String username) {
    repository.deleteByUsername(username);
  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {
    // TODO Auto-generated method stub
  }

  @Transactional(readOnly = true)
  @Override
  public boolean userExists(String username) {
    return repository.existsByUsername(username);
  }
}
