package com.kovalevskyi.academy.codingbootcamp.week1.day1;

import java.util.stream.IntStream;

/**
 * w1d1 task.
 */
public class StringUtils {

  static {
    DISTANCE_BETWEEN_CHARACTERS = 'a' - 'A';
  }

  public static final int ASCII_MAX_VALUE = 255;

  private static final int DISTANCE_BETWEEN_CHARACTERS;

  /**
   * Uppercase symbol checking.
   *
   * @param ch symbol
   * @return true if symbol is uppercase
   */
  public static boolean isAsciiUppercase(char ch) {
    if (ch > ASCII_MAX_VALUE) {
      throw new IllegalArgumentException();
    }

    return 'A' <= ch && ch <= 'Z';
  }

  /**
   * Lowercase symbol checking.
   *
   * @param ch symbol
   * @return true if symbol is lowercase
   */
  public static boolean isAsciiLowercase(char ch) {
    if (ch > ASCII_MAX_VALUE) {
      throw new IllegalArgumentException();
    }

    return 'a' <= ch && ch <= 'z';
  }

  /**
   * Numeric symbol checking.
   *
   * @param ch symbol
   * @return true if symbol is numeric
   */
  public static boolean isAsciiNumeric(char ch) {
    if (ch > ASCII_MAX_VALUE) {
      throw new IllegalArgumentException();
    }

    return '0' <= ch && ch <= '9';
  }

  /**
   * Alphabetic symbol checking.
   *
   * @param ch symbol
   * @return true if symbol is alphabetic
   */
  public static boolean isAsciiAlphabetic(char ch) {
    return isAsciiUppercase(ch) || isAsciiLowercase(ch);
  }

  /**
   * Convert symbol to uppercase.
   *
   * @param ch symbol
   * @return uppercase symbol
   */
  public static char toAsciiUppercase(char ch) {
    if (isAsciiLowercase(ch)) {
      return (char) (ch - DISTANCE_BETWEEN_CHARACTERS);
    }
    return ch;
  }

  /**
   * Convert symbol to lowercase.
   *
   * @param ch symbol
   * @return lowercase symbol
   */
  public static char toAsciiLowercase(char ch) {
    if (isAsciiUppercase(ch)) {
      return (char) (ch + DISTANCE_BETWEEN_CHARACTERS);
    }
    return ch;
  }

  /**
   * Convert array of chars to StringBuilder with all uppercase symbols.
   *
   * @param input array of symbols
   * @return StringBuilder with all uppercase symbols
   */
  public static StringBuilder makeUppercase(char[] input) {
    final var result = new StringBuilder();
    for (char symbol : input) {
      result.append(toAsciiUppercase(symbol));
    }
    return result;
  }

  /**
   * Convert array of chars to StringBuilder with all lowercase symbols.
   *
   * @param input array of symbols
   * @return StringBuilder with all lowercase symbols
   */
  public static StringBuilder makeLowercase(char[] input) {
    final var result = new StringBuilder();
    for (char symbol : input) {
      result.append(toAsciiLowercase(symbol));
    }
    return result;
  }

  /**
   * Convert array of chars to StringBuilder with camel style symbols.
   *
   * @param input array of symbols
   * @return StringBuilder with camel style symbols
   */
  public static StringBuilder makeCamel(char[] input) {
    final var result = new StringBuilder();
    var toUpperCase = false;
    for (char symbol : input) {
      if (toUpperCase) {
        result.append(toAsciiUppercase(symbol));
        toUpperCase = false;
      } else {
        result.append(toAsciiLowercase(symbol));
        toUpperCase = true;
      }
    }
    return result;
  }

  /**
   * Numeric symbol array checking.
   *
   * @param input array of symbols
   * @return true if all symbol in array are numeric
   */
  public static boolean isStringAlphaNumerical(char[] input) {
    for (char symbol : input) {
      if (!isAsciiAlphabetic(symbol) && !isAsciiNumeric(symbol) && symbol != ' ') {
        return false;
      }
    }
    return true;
  }

  /**
   * Convert array of arrays of symbols to simple array.
   *
   * @param input array of arrays
   * @return array with all symbols of input array
   */
  public static char[] concatStrings(char[][] input) {
    final var stringBuilder = new StringBuilder();
    IntStream.range(0, input.length)
        .forEach(index -> {
          IntStream.range(0, input[index].length)
              .forEach(entry -> {
                final char symbol = input[index][entry];
                if (symbol > ASCII_MAX_VALUE) {
                  throw new IllegalArgumentException();
                } else {
                  stringBuilder.append(symbol);
                }
              });
        });
    return stringBuilder.toString().toCharArray();
  }

  /**
   * Convert array of symbols to int.
   *
   * @param input array of symbols
   * @return int value
   */
  public static int toInt(final char[] input) {
    if (input.length == 0) {
      throw new IllegalArgumentException();
    }

    final boolean isNegative = input[0] == '-';
    int result = 0;
    int index = isNegative ? 1 : 0;
    int distanceBetweenSymbols = input.length - index;
    while (index < input.length) {
      if (isAsciiNumeric(input[index])) {
        result += pow(Character.getNumericValue(input[index++]), distanceBetweenSymbols);
        distanceBetweenSymbols--;
      } else {
        throw new NumberFormatException(String.format("%s is not a number!", input[index]));
      }
    }
    return isNegative ? -result : result;
  }

  private static int pow(final int base, final int pow) {
    int result = base;
    for (int i = 1; i < pow; i++) {
      result *= 10;
    }
    return result;
  }
}
