package ru.otus.quiz.domain;

public class User implements Comparable<User> {

  private final String firstName;
  private final String lastName;
  private int score;

  public User(String firstName, String lastName, int score) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.score = score;
  }

  public User(String firstName, String lastName) {
    this(firstName, lastName, 0);
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public int compareTo(User o) {
    return (firstName + lastName).compareTo(o.firstName + o.lastName);
  }
}
