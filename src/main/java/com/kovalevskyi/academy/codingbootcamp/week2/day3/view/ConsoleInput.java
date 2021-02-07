package com.kovalevskyi.academy.codingbootcamp.week2.day3.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleInput implements IInput {

  private final InputStream input;

  public ConsoleInput(InputStream input) {
    this.input = input;
  }

  @Override
  public String ask() {
    var text = (String) null;
    var reader = new BufferedReader(new InputStreamReader(input));
    try {
      text = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return text;
  }

}
