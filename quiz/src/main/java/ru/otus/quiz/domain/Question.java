package ru.otus.quiz.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

  private final int id;
  private final int correctAnswer;
  private final String text;
  private final List<Answer> answers;

  public Question(int id, String text, int correctAnswer) {
    this.id = id;
    this.text = text;
    this.correctAnswer = correctAnswer;
    this.answers = new ArrayList<>();
  }

  public String getText() {
    return text;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public Question addAnswer(Answer answer) {
    this.answers.add(answer);
    return this;
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

  public int getId() {
    return id;
  }

  public int getCorrectAnswer() {
    return correctAnswer;
  }
}
