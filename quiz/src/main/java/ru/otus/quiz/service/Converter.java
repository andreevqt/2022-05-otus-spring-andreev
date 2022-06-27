package ru.otus.quiz.service;

public interface Converter<T, S> {
  T convert(S source);
}
