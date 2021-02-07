package com.kovalevskyi.academy.codingbootcamp.week2.day1;

import java.util.Objects;

/**
 * w2d1 task.
 */
public class TextPrinter2 {

  private final String[] arguments;

  public TextPrinter2(final String[] arguments) {
    this.arguments = arguments;
  }

  /**
   * Main logic.
   */
  public void run() {
    final boolean isNull = Objects.isNull(arguments);
    if (isNull || arguments.length != 2) {
      var amount = isNull ? 0 : arguments.length;
      System.out.printf("Please provide 2 input argument, current amount: %d%n", amount);
    } else {
      final var border = Objects.requireNonNullElse(arguments[0], "*");
      final var message = Objects.requireNonNullElse(arguments[1], "");
      final var length = message.isEmpty() ? 4 : message.length() + 4;
      final var rightBorder = String.format("%s%n", border);
      LineGenerator.generateLine(border, rightBorder, border, length).forEach(System.out::print);
      System.out.printf("%s %s %s%n", border, message, border);
      LineGenerator.generateLine(border, rightBorder, border, length).forEach(System.out::print);
    }
  }

  public static void main(String[] args) {
    new TextPrinter2(args).run();
  }
}
