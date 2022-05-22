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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Question question = (Question) o;

    if (id != question.id) return false;
    if (text != null ? !text.equals(question.text) : question.text != null) return false;
    if (ans1 != null ? !ans1.equals(question.ans1) : question.ans1 != null) return false;
    return ans2 != null ? ans2.equals(question.ans2) : question.ans2 == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (text != null ? text.hashCode() : 0);
    result = 31 * result + (ans1 != null ? ans1.hashCode() : 0);
    result = 31 * result + (ans2 != null ? ans2.hashCode() : 0);
    return result;
  }
}
