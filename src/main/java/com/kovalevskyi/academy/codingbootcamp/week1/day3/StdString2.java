package com.kovalevskyi.academy.codingbootcamp.week1.day3;

import com.kovalevskyi.academy.codingbootcamp.week1.day1.StdString1;
import com.kovalevskyi.academy.codingbootcamp.week1.day1.StringUtils;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * w1d3 task.
 */
public class StdString2 extends StdString1 {

  /**
   * Constructor that creates string from the specified char array.
   *
   * @param base char array to use as a string base
   */
  public StdString2(char[] base) {
    super(base);
  }

  /**
   * Constructor that creates empty string with length 0.
   */
  public StdString2() {
    this(new char[0]);
  }

  /**
   * Constructor that creates a copy of the string. New String should have exactly same characters
   * as the original one.
   *
   * @param that to copy from.
   */
  public StdString2(StdString2 that) {
    super(that);
  }

  /**
   * Creates a copy of the string with all ascii characters in lower case.
   *
   * @return new string with all the characters lowercase
   * @throws IllegalArgumentException if one of characters is not ascii
   */
  public StdString2 toAsciiLowerCase() {
    final var result = new char[this.base.length];
    IntStream
        .range(0, this.base.length)
        .forEach(ch -> result[ch] = StringUtils.toAsciiLowercase(this.base[ch]));
    return new StdString2(result);
  }

  /**
   * Creates a copy of the string with all ascii characters in upper case.
   *
   * @return new string with all the characters uppercase
   * @throws IllegalArgumentException if one of characters is not ascii
   */
  public StdString2 toAsciiUpperCase() {
    final var result = new char[this.base.length];
    IntStream
        .range(0, this.base.length)
        .forEach(ch -> result[ch] = StringUtils.toAsciiUppercase(this.base[ch]));
    return new StdString2(result);
  }

  /**
   * Return new string that contains all the characters of this string from the specific range. From
   * is inclusive, to is exclusive.
   *
   * @param from inclusive index
   * @param to   exclusive index
   * @return substring
   * @throws IndexOutOfBoundsException if index is incorrect
   * @throws IllegalArgumentException  if from >= to
   */

  public StdString2 subString(int from, int to) {
    return new StdString2(Arrays.copyOfRange(this.base, from, to));
  }

  /**
   * Creates new string that includes all characters from this string and all characters in all the
   * input strings.
   *
   * @param that strings to add
   * @return new string that combines all strings together
   * @throws NullPointerException if input is null
   */
  public StdString2 concat(StdString2... that) {
    var result = this.base;
    for (var item : that) {
      final var tmp = new char[result.length + item.length()];
      System.arraycopy(result, 0, tmp, 0, result.length);
      System.arraycopy(item.toCharArray(), 0, tmp, result.length, item.length());
      result = tmp;
    }
    return new StdString2(result);
  }

  /**
   * Split one string on array of substrings, based on separator.
   *
   * @param separator to use for splitting the string
   * @return array of strings
   */
  public StdString2[] split(char separator) {
    final var result = new StdString2[this.base.length];
    var elements = 0;
    var from = 0;
    var elementsWithOutLastEmpty = 0;
    for (var to = 0; to < this.base.length; to++) {
      if (this.base[to] == separator) {
        result[elements++] = this.subString(from, to);
        if (to - from != 0) {
          elementsWithOutLastEmpty = elements;
        }
        from = to + 1;
      }
    }
    if (this.base.length - from != 0) {
      result[elements++] = this.subString(from, this.base.length);
      elementsWithOutLastEmpty = elements;
    }
    return Arrays.copyOf(result, elementsWithOutLastEmpty);
  }

  /**
   * Removes all the whitespaces at the beginning and at the end of the string and returns result in
   * a new string.
   *
   * @return new string without whitespaces
   */
  public StdString2 trim() {
    if (this.base.length == 0) {
      return this;
    }
    var from = 0;
    var to = this.base.length - 1;
    for (; from < this.base.length; from++) {
      if (this.base[from] != ' ') {
        break;
      }
    }
    for (; to >= 0; to--) {
      if (this.base[to] != ' ') {
        to++;
        break;
      }
    }
    return this.subString(from, to);
  }

  /**
   * Removes all cases of a specified character in a string and returns new string without it.
   *
   * @param toRemove character to search for
   * @return new string without specified character
   */
  public StdString2 removeCharacter(char toRemove) {
    final var result = new char[this.base.length];
    var elements = 0;
    for (char symbol : this) {
      if (symbol != toRemove) {
        result[elements++] = symbol;
      }
    }
    return new StdString2(Arrays.copyOf(result, elements));
  }
}
