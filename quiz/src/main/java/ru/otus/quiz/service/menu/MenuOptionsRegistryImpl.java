package ru.otus.quiz.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MenuOptionsRegistryImpl implements MenuOptionsRegistry {
  private final Map<Integer, MenuOption> options;

  @Autowired
  public MenuOptionsRegistryImpl(List<MenuOption> options) {
    this.options = options.stream()
      .collect(Collectors.toUnmodifiableMap(MenuOption::getId, Function.identity()));
  }

  @Override
  public List<MenuOption> getAvailableMenuOptions() {
    return options.values().stream().collect(Collectors.toUnmodifiableList());
  }

  @Override
  public Optional<MenuOption> getMenuOptionById(int id) {
    return Optional.ofNullable(options.get(id));
  }
}