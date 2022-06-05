package ru.otus.quiz.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.quiz.config.UsersResourceProvider;
import ru.otus.quiz.csv.CSVReader;
import ru.otus.quiz.csv.CSVWriter;
import ru.otus.quiz.domain.User;
import ru.otus.quiz.exceptions.CSVLoadingException;
import ru.otus.quiz.exceptions.CSVWritingException;
import ru.otus.quiz.service.UserConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoCSV implements UserDao {

  private final UsersResourceProvider resourceProvider;
  private final UserConverter userConverter;

  @Autowired
  public UserDaoCSV(UsersResourceProvider resourceProvider, UserConverter userConverter) {
    this.resourceProvider = resourceProvider;
    this.userConverter = userConverter;
  }

  private List<User> loadData() {
    try {
      var users = new ArrayList<User>();
      var resourcePath = resourceProvider.getUsersResource();
      var resource = getClass().getResourceAsStream(resourcePath);
      try (var reader = new CSVReader(new InputStreamReader(resource))) {
        String[] line;
        while ((line = reader.nextLine()) != null) {
          var user = new User(line[0], line[1], Integer.parseInt(line[2]));
          users.add(user);
        }
      }
      return users;
    } catch (IOException e) {
      throw new CSVLoadingException(e);
    }
  }

  @Override
  public List<User> findAll() {
    return loadData();
  }

  @Override
  public void save(User u) {
    var resourcePath = resourceProvider.getUsersResource();
    var resource = getClass().getResource(resourcePath).getPath();

    try {
      try (var writer = new CSVWriter(new FileWriter(resource, true))) {
        writer.writeNext(userConverter.convertToArray(u));
      }
    } catch (IOException e) {
      throw new CSVWritingException(e);
    }
  }
}
