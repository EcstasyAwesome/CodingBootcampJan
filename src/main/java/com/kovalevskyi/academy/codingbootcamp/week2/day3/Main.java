package com.kovalevskyi.academy.codingbootcamp.week2.day3;

import com.kovalevskyi.academy.codingbootcamp.week2.day3.controller.Engine;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.model.Board;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.view.ConsoleInput;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.view.ConsoleOutput;

public class Main {

  public static void main(String[] args) {
    new Engine(new Board(), new ConsoleInput(System.in), new ConsoleOutput(System.out)).run();
  }
}
