package ru.otus.quiz.csv;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

public class CSVReader implements Closeable {
  private static final String SEPARATOR = ",";
  private final BufferedReader reader;

  public CSVReader(Reader reader) {
    this.reader = new BufferedReader(reader);
  }

  public String[] nextLine() throws IOException {
    var line = reader.readLine();
    if (line == null) {
      return null;
    }

    return line.split(SEPARATOR);
  }

  @Override
  public void close() throws IOException {
    reader.close();
  }
}
