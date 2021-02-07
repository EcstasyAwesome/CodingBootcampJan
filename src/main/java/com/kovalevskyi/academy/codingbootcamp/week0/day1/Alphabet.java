package com.kovalevskyi.academy.codingbootcamp.week0.day1;

/**
 * w0d1 task.
 */
public class Alphabet {

  /**
   * Generate the Latin alphabet.
   *
   * @return an array that contains alphabet.
   */
  public static char[] generateAlphabet() {
    return getAlphabet(false);
  }

  /**
   * Generate the reversed Latin alphabet.
   *
   * @return an array that contains reversed alphabet.
   */
  public static char[] generateReversedAlphabet() {
    return getAlphabet(true);
  }

  /**
   * Generate the Latin alphabet according order.
   *
   * @param reversedOrder natural or reversed order
   * @return an array that contains alphabet.
   */
  private static char[] getAlphabet(final boolean reversedOrder) {
    char a = 'a';
    final char z = 'z';
    final char[] alphabet = new char[(z - a) + 1];
    int index = reversedOrder ? alphabet.length - 1 : 0;
    while (a <= z) {
      alphabet[reversedOrder ? index-- : index++] = a++;
    }
    return alphabet;
  }
}