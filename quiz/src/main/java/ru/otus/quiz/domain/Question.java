package ru.otus.quiz.domain;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Question {

  private final String text;
  private final ArrayList<Answer> answers;

  public Question(String text, ArrayList<Answer> answers) {
    this.text = text;
    this.answers = answers;
  }

  public Question(String text, Answer... answers) {
    this(text, new ArrayList<>(Arrays.asList(answers)));
  }

  public String getText() {
    return text;
  }

  public ArrayList<Answer> getAnswers() {
    return answers;
  }

  @Override
  public String toString() {
    var answers = this.answers.stream().map(Answer::getText).collect(Collectors.joining(", "));
    return String.format("%s %s", text, answers);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Question question = (Question) o;

    if (!text.equals(question.text)) return false;
    return answers.equals(question.answers);
  }

  @Override
  public int hashCode() {
    int result = text.hashCode();
    result = 31 * result + answers.hashCode();
    return result;
  }
}
