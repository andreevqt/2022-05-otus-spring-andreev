package ru.otus.quiz.service.converters;

public interface Converter<T, S> {
  T convert(S source);
}
