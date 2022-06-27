package ru.otus.quiz.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Stream;


public class IOServiceImpl implements IOService {

  private final PrintStream output;
  private final Scanner input;

  public IOServiceImpl(InputStream input, PrintStream output) {
    this.output = output;
    this.input = new Scanner(input);
  }

  @Override
  public void out(String str) {
    output.println(str);
  }

  @Override
  public int readInt() {
    return Integer.parseInt(input.next());
  }

  @Override
  public int readIntWithPrompt(String prompt) {
    out(prompt);
    return Integer.parseInt(input.next());
  }

  @Override
  public String readStringWithPrompt(String prompt) {
    out(prompt);
    return input.nextLine();
  }
}
