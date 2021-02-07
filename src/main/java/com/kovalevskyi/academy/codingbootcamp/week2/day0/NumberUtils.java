package com.kovalevskyi.academy.codingbootcamp.week2.day0;

/**
 * w2d0 task.
 */
public class NumberUtils {

  /**
   * The method calculates the factorial of a number.
   *
   * @param number - the number, the factorial of which is to be calculated. number >= 0.
   * @return - the result of the calculation.
   */
  public static int getFactorial(int number) {
    if (number < 0) {
      throw new IllegalArgumentException();
    }
    var result = 1;
    while (number > 0) {
      result *= number--;
    }
    return result;
  }

  /**
   * Compute the factorial of specified number with recursive.
   *
   * @param number - specified.
   * @return factorial of the number.
   */
  public static int factorialRecursive(int number) {
    if (number < 0) {
      throw new IllegalArgumentException();
    }
    if (number == 1) {
      return 1;
    }
    return number * factorialRecursive(--number);
  }

  /**
   * Calculates the degree of the base.
   *
   * @param base  the number to be raised to the power.
   * @param power the actual degree.
   * @return base ^ power.
   */
  public static long power(int base, int power) {
    if (power < 0) {
      throw new IllegalArgumentException();
    }
    if (power == 0) {
      return 1;
    }
    var result = base;
    while (power-- > 1) {
      result *= base;
    }
    return result;
  }

  /**
   * Calculates the degree of the base with recursive.
   *
   * @param base  the number to be raised to the power.
   * @param power the actual degree.
   * @return base ^ power.
   */
  public static int powerRecursive(int base, int power) {
    if (power < 0) {
      throw new IllegalArgumentException();
    }
    if (power == 0) {
      return 1;
    }
    return base * powerRecursive(base, --power);
  }

  /**
   * The method calculates the Fibonacci number based on the incoming index.
   *
   * @param index the ordinal number of the fibonacci number.
   * @return fibonacci number.
   */
  public static int fibRecursive(int index) {
    if (index < 0) {
      throw new IllegalArgumentException();
    }
    if (index < 2) {
      return index;
    }
    return fibRecursive(index - 1) + fibRecursive(index - 2);
  }

  /**
   * This method should calculate and return the entire series of fibonacci numbers. The length of
   * the row is specified by the argument included in the method.
   *
   * @param length the size of sequence.
   * @return fibonacci sequence.
   */
  public static int[] fibSequence(int length) {
    if (length < 0) {
      throw new IllegalArgumentException();
    }
    if (length == 0) {
      return new int[0];
    }
    final var fib = new int[length];
    fib[0] = 1;
    if (length > 1) {
      fib[1] = 1;
      for (var index = 2; index < length; index++) {
        fib[index] = fib[index - 1] + fib[index - 2];
      }
    }
    return fib;
  }

  /**
   * Let's write a method that calculates the square root of an input number. It finds only integer
   * variants of the square root of a number. If the integer square root cannot be found from the
   * number, return -1.
   *
   * @param target some number.
   * @return integer sqrt.
   */
  public static int sqrt(int target) {
    if (target < 0) {
      throw new IllegalArgumentException();
    }
    var result = 0;
    var odd = 1;
    while (target != 0) {
      if (target < odd) {
        return -1;
      }
      target = target - odd;
      odd += 2;
      result++;
    }
    return result;
  }

  /**
   * This method determines the simplicity of the input number. He answers the question: is the
   * number prime?
   *
   * @param target - the number.
   * @return the answer on the question.
   */
  public static boolean isPrime(int target) {
    if (target < 0) {
      throw new IllegalArgumentException();
    }
    if (target == 0) {
      return false;
    }
    if (target == 1) {
      return false;
    }
    for (var i = 2; i < target; i++) {
      if (target % i == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method should find the closest prime number up from the input number. If the input number
   * is already prime, we return it.
   *
   * @param target incoming number.
   * @return nearest prime number.
   */
  public static int findNextPrime(int target) {
    while (true) {
      if (isPrime(target)) {
        return target;
      } else {
        target++;
      }
    }
  }
}
