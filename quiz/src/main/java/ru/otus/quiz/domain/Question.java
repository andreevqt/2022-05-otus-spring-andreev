package ru.otus.quiz.domain;

public class Question {
  private final int id;
  private final String text;
  private final String ans1;
  private final String ans2;

  public Question(int id, String text, String ans1, String ans2) {
    this.id = id;
    this.text = text;
    this.ans1 = ans1;
    this.ans2 = ans2;
  }

  public int getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public String getAns1() {
    return ans1;
  }

  public String getAns2() {
    return ans2;
  }

  @Override
  public String toString() {
    return String.format("%o %s %s %s ", id, text, ans1, ans2);
  }

}
