package com.kovalevskyi.academy.codingbootcamp.week2.day0;

import com.kovalevskyi.academy.codingbootcamp.week1.day0.Sorting;

/**
 * w2d0 task.
 */
public class MainPrintSortedParam {

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
    Sorting.sort(args, String::compareTo);
    for (var arg : args) {
      System.out.println(arg);
    }
  }
}
