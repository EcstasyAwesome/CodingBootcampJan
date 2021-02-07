package com.kovalevskyi.academy.codingbootcamp.week0.day1;

import java.util.stream.IntStream;

/**
 * w0d1 task.
 */
public class Numbers1 {

  /**
   * The method must generate an array of numbers from 0 inclusive to 100 exclusively.
   *
   * @return an array that contains numbers from 0 to 99, inclusive.
   */
  public static int[] generateNumbers() {
    return IntStream.range(0, 100).toArray();
  }

  /**
   * Finds the greater of the two.
   *
   * @param left  number.
   * @param right number.
   * @return the biggest number.
   */
  public static int findBiggest(int left, int right) {
    return left > right ? left : right;
  }

  /**
   * Finds the larger of the three.
   *
   * @param left  number.
   * @param mid   number.
   * @param right number.
   * @return the biggest number.
   */
  public static int findBiggest(int left, int mid, int right) {
    return findBiggest(findBiggest(left, mid), right);
  }

  /**
   * Searches for the largest element in a non-empty array.
   *
   * @param numbers — an array that contains random numbers from -49 to +49.
   * @return the biggest number.
   */
  public static int findBiggest(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
      throw new IllegalArgumentException();
    }
    if (numbers.length == 1) {
      return numbers[0];
    }

    var biggest = numbers[0];
    for (var index = 1; index < numbers.length; index++) {
      biggest = findBiggest(biggest, numbers[index]);
    }
    return biggest;
  }

  /**
   * Finds the index of the largest element in an array.
   *
   * @param numbers the array with different numbers from -49 to +49.
   * @return the index (position) of the largest element in the array.
   */
  public static int findIndexOfBiggestNumber(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
      throw new IllegalArgumentException();
    }
    if (numbers.length == 1) {
      return 0;
    }

    var biggest = numbers[0];
    var position = 0;
    for (var index = 1; index < numbers.length; index++) {
      if (biggest < numbers[index]) {
        biggest = numbers[position = index];
      }
    }
    return position;
  }

  /**
   * The method should answer the question.
   *
   * @param number positive or negative.
   * @return the answer.
   */
  public static boolean isNegative(int number) {
    return number < 0;
  }

  /**
   * Converts any integer number to a character array.
   *
   * @param number incoming integer number.
   * @return an array that represents the input number character by character.
   */
  public static char[] convertToCharArray(int number) {
    if (number == 0) {
      return new char[]{'0'};
    }
    var length = isNegative(number) ? 1 : 0;
    var tmpNumber = number;
    while (tmpNumber != 0) {
      tmpNumber /= 10;
      length++;
    }
    var result = new char[length];
    if (isNegative(number)) {
      result[0] = '-';
    }
    while (number != 0) {
      var partOfNumber = number % 10;
      var symbol = (char) ('0' + (isNegative(partOfNumber) ? -partOfNumber : partOfNumber));
      result[--length] = symbol;
      number /= 10;
    }
    return result;
  }
}