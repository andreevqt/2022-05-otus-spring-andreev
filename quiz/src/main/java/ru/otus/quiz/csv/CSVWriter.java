package ru.otus.quiz.csv;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class CSVWriter implements Closeable, Flushable {

  private static final String SEPARATOR = ",";
  private static final String LINE_END = "\n";

  private final Writer writer;

  public CSVWriter(Writer writer) {
    this.writer = writer;
  }

  public void writeNext(String[] lines) throws IOException {
    var sb = new StringBuilder();
    for (int i = 0; i < lines.length; i++) {
      if (i != 0) {
        sb.append(SEPARATOR);
      }
      sb.append(lines[i]);
    }

    sb.append(LINE_END);
    writer.write(sb.toString());
  }

  @Override
  public void close() throws IOException {
    this.writer.close();
  }

  @Override
  public void flush() throws IOException {
    this.writer.flush();
  }
}
