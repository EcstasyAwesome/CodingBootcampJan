package com.kovalevskyi.academy.codingbootcamp.week2.day0;

import com.kovalevskyi.academy.codingbootcamp.week1.day1.StringUtils;

/**
 * w2d0 task.
 */
public class Calculator {

  /**
   * Input point.
   *
   * @param args = 3.
   */
  public static void main(String... args) {
    if (args.length != 3) {
      System.out.println("Please provide 3 input arguments, example: 2 + 3");
      return;
    }
    var left = StringUtils.toInt(args[0].toCharArray());
    var action = args[1];
    var right = StringUtils.toInt(args[2].toCharArray());
    switch (action) {
      case "+":
        print((long) left + right);
        break;
      case "-":
        print((long) left - right);
        break;
      case "/":
        if (right == 0) {
          System.out.println("Division by zero is impossible!");
        } else {
          print(left / right);
        }
        break;
      case "*":
        print((long) left * right);
        break;
      case "%":
        print(left % right);
        break;
      default:
        throw new IllegalArgumentException();
    }
  }

  private static void print(long result) {
    System.out.printf("result: %s%n", result);
  }
}
