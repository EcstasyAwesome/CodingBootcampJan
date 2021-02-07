package com.kovalevskyi.academy.codingbootcamp.week0.day2;

import static com.kovalevskyi.academy.codingbootcamp.week0.day1.Numbers1.convertToCharArray;

/**
 * w0d2 task.
 */
public class Numbers2 {

  /**
   * Sorts the elements in an array in ascending order.
   *
   * @param target an array of integers.
   */
  public static void sort(int[] target) {
    if (target == null || target.length == 0) {
      return;
    }
    for (var current = 0; current < target.length; current++) {
      for (var search = current + 1; search < target.length; search++) {
        if (target[current] > target[search]) {
          //it's swap without creating a local variable
          target[current] = target[current] + target[search];
          target[search] = target[current] - target[search];
          target[current] = target[current] - target[search];
        }
      }
    }
  }

  /**
   * The method calculates the factorial of a number.
   *
   * @param number - the number, the factorial of which is to be calculated.
   * @return - the result of the calculation.
   */
  public static int getFactorial(final int number) {
    if (number < 0) {
      throw new IllegalArgumentException();
    }

    var result = 1;
    var tmp = number;
    while (tmp > 0) {
      result *= tmp--;
    }
    return result;
  }

  /**
   * The method returns an array with ordered sub arrays, each of the sub arrays contains a unique
   * combination of digits from 0 to 9, inclusive.
   *
   * @return something like [[012], [013], [...], [...], [...], [789]]
   */
  public static char[][] generateTriplets() {
    final var factOfThree = getFactorial(3);
    final var factOfTen = getFactorial(10);
    final var length = factOfTen / (factOfThree * getFactorial(10 - 3));
    final var result = new char[length][3];
    var arrayIndex = 0;
    for (var index1 = 0; index1 < 10; index1++) {
      for (var index2 = index1 + 1; index2 < 10; index2++) {
        for (var index3 = index2 + 1; index3 < 10; index3++) {
          result[arrayIndex++] = new char[]{
              (char) (index1 + '0'),
              (char) (index2 + '0'),
              (char) (index3 + '0')
                };
        }
      }
    }
    return result;
  }

  /**
   * Generate an array with sub arrays of unique combinations of two numbers from [['0', '0', ' ',
   * '0', '0'] ...to... ['9', '9', ' ', '9', '9']], in ascending order.
   *
   * @return an array with sub arrays.
   */
  public static char[][] generateTuples() {
    final var result = new char[powOfTen(4)][5];
    var arrayIndex = 0;
    for (var index1 = 0; index1 < 10; index1++) {
      for (var index2 = 0; index2 < 10; index2++) {
        for (var index3 = 0; index3 < 10; index3++) {
          for (var index4 = 0; index4 < 10; index4++) {
            result[arrayIndex++] = new char[]{
                (char) (index1 + '0'),
                (char) (index2 + '0'),
                ' ',
                (char) (index3 + '0'),
                (char) (index4 + '0')
                  };
          }
        }
      }
    }
    return result;
  }

  /**
   * Generate an array with sub arrays, which contains unique combinations of numbers. If n = 2 =>
   * [['0', '1'], ['0', '2'], [...], [], []]
   *
   * @param amount of digits, for unique combinations.
   * @return an array with sub arrays of unique combinations of n numbers. Where (0 < n < 10).
   */
  public static char[][] generateTuples(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException();
    }
    if (amount == 0) {
      return new char[0][0];
    }
    final var length = powOfTen(amount);
    final var result = new char[length][amount];
    for (var index = 0; index < length; index++) {
      var numberArrayOfIndex = convertToCharArray(index);
      var innerIndex = 0;
      while (innerIndex < amount - numberArrayOfIndex.length) {
        result[index][innerIndex++] = '0';
      }
      for (var symbol : numberArrayOfIndex) {
        result[index][innerIndex++] = symbol;
      }
    }
    return result;
  }

  /**
   * Get ten to the power of 'n'.
   *
   * @param rank pow number
   * @return pow of ten
   */
  private static int powOfTen(final int rank) {
    int result = 10;
    for (int i = 1; i < rank; i++) {
      result *= 10;
    }
    return result;
  }
}
