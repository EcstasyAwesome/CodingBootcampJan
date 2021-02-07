package com.kovalevskyi.academy.codingbootcamp.week2.day2;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * w2d2 task.
 */
@Command(
    name = "Loading",
    version = "1.0",
    description = "Shows how exactly loading looks like on consoles",
    mixinStandardHelpOptions = true
)
public class Console implements Runnable {

  @Option(
      required = true,
      names = {"-s", "--steps"},
      paramLabel = "number",
      description = "Amount of loading elements")
  private int steps;

  @Parameters(
      paramLabel = "seconds",
      arity = "1",
      description = "Time values"
  )
  private int[] seconds;

  @Override
  public void run() {
    new TimeProgressBar(steps, seconds).printProgressBar(System.out);
  }

  public static void main(String[] args) {
    new CommandLine(new Console()).execute(args);
  }
}