package ru.otus.quiz.domain;

public class Answer {

  private final boolean correct;
  private final String text;

  public Answer(String text, boolean correct) {
    this.text = text;
    this.correct = correct;
  }

  public Answer(String text) {
    this(text, false);
  }

  public boolean isCorrect() {
    return correct;
  }

  public String getText() {
    return text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Answer answer = (Answer) o;

    if (correct != answer.correct) return false;
    return text.equals(answer.text);
  }

  @Override
  public int hashCode() {
    int result = (correct ? 1 : 0);
    result = 31 * result + text.hashCode();
    return result;
  }
}
