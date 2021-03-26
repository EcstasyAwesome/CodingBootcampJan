package com.kovalevskyi.academy.codingbootcamp.week2.day2;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * w2d2 task.
 */
@Command(
    name = "Progress bar",
    version = "1.0",
    description = "Shows how exactly loading looks like on consoles",
    mixinStandardHelpOptions = true
)
public class Console implements Runnable {

  @Parameters(
      index = "0",
      paramLabel = "elements",
      arity = "1",
      description = "Amount of elements")
  private int elements;

  @Parameters(
      index = "1",
      paramLabel = "seconds",
      arity = "1..",
      description = "Time values"
  )
  private int[] seconds;

  @Override
  public void run() {
    new TimeProgressBar(elements, seconds).printProgressBar(System.out);
  }

  public static void main(String[] args) {
    new CommandLine(new Console()).execute(args);
  }
}