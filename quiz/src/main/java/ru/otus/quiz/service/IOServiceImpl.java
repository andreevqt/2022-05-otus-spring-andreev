package ru.otus.quiz.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

  private final PrintStream output;
  private final Scanner input;

  public IOServiceImpl() {
    this.output = System.out;
    this.input = new Scanner(System.in);
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
