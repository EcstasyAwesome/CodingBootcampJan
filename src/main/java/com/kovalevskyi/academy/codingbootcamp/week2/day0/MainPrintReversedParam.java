package com.kovalevskyi.academy.codingbootcamp.week2.day0;

/**
 * w2d0 task.
 */
public class MainPrintReversedParam {

  /**
   * Main method.
   *
   * @param args input arguments
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please specify at least one argument!");
      return;
    }
    for (var index = args.length - 1; index >= 0; index--) {
      System.out.println(args[index]);
    }
  }
}
