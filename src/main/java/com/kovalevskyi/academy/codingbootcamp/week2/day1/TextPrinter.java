package com.kovalevskyi.academy.codingbootcamp.week2.day1;

import java.util.Objects;

/**
 * w2d1 task.
 */
public class TextPrinter {

  private final String[] arguments;

  public TextPrinter(final String[] arguments) {
    this.arguments = arguments;
  }

  /**
   * Main logic.
   */
  public void run() {
    final var isNull = Objects.isNull(arguments);
    if (isNull || arguments.length != 1) {
      var amount = isNull ? 0 : arguments.length;
      System.out.printf("Please provide only one input argument, current amount: %d%n", amount);
    } else {
      final var message = Objects.requireNonNullElse(arguments[0], "");
      final var length = message.isEmpty() ? 2 : message.length() + 2;
      LineGenerator
          .generateLine("/#", String.format("#\\%n"), "#", length)
          .forEach(System.out::print);
      System.out.printf("# %s #%n", message);
      LineGenerator
          .generateLine("\\#", String.format("#/%n"), "#", length)
          .forEach(System.out::print);
    }
  }

  public static void main(String[] args) {
    new TextPrinter(args).run();
  }
}
