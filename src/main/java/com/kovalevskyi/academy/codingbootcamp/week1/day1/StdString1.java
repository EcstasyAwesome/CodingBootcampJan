package com.kovalevskyi.academy.codingbootcamp.week1.day1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * w1d1 task.
 */
public class StdString1 implements Iterable<Character> {

  protected final char[] base;

  /**
   * Constructor that creates string from the specified char array.
   *
   * @param base char array to use as a string base
   */
  public StdString1(char[] base) {
    this.base = base.clone();
  }

  /**
   * Constructor that creates empty string with length 0.
   */
  public StdString1() {
    this(new char[0]);
  }

  /**
   * Constructor that creates a copy of the string. New String should have exactly same characters
   * as the original one.
   *
   * @param stdString1 to copy from.
   */
  public StdString1(StdString1 stdString1) {
    this(stdString1.base);
  }

  /**
   * Returns amount of characters in the string. For example for the string "cat" it should return
   * 3
   *
   * @return amount of characters in the string.
   */
  public int length() {
    return this.base.length;
  }

  /**
   * Creates new class StdString that includes both strings in it. For example, if there is a string
   * "cat" and another string "dog", append method should return a new string with the value
   * "catdog".
   *
   * @param that another string that should be appended to this string
   * @return new StdString that includes new value
   * @throws NullPointerException – if that is null.
   */
  public StdString1 append(StdString1 that) {
    final char[] result = new char[this.base.length + that.base.length];
    System.arraycopy(this.base, 0, result, 0, this.base.length);
    System.arraycopy(that.toCharArray(), 0, result, this.base.length, that.base.length);
    return new StdString1(result);
  }

  /**
   * Returns value of the string in a form of char array.
   *
   * @return value of the string in a form of char array.
   * @throws NullPointerException if string is empty.
   */
  public char[] toCharArray() {
    return this.base;
  }

  /**
   * Returns character from the specific index of the string. Examples "cat", 0 => 'c' "dog", 2 =>
   * 'g'
   *
   * @param index of the character that needs to be returned.
   * @return character.
   * @throws IndexOutOfBoundsException if index > length or index < 0.
   */
  public char charAt(int index) {
    return this.base[index];
  }

  /**
   * Finds first index of a specific character in the string, or -1 if character not found. For
   * example: "cat", 'a' => 0 "asdef23q4asdfasdf", 'd' => 2 "dog", 'q' => -1
   *
   * @param target character to search for
   * @return index of the character or -1
   */
  public int indexOf(char target) {
    for (int index = 0; index < this.length(); index++) {
      if (this.base[index] == target) {
        return index;
      }
    }
    return -1;
  }

  /**
   * Equals that follows all best practices and compares all characters in the current string with
   * the characters in the other string.
   *
   * @param that to compare with
   * @return true or false
   */
  @Override
  public boolean equals(final Object that) {
    if (this == that) {
      return true;
    }
    if (that == null || this.getClass() != that.getClass()) {
      return false;
    }
    final StdString1 stdString1 = (StdString1) that;
    return Arrays.equals(this.base, stdString1.base);
  }

  /**
   * Hash code is calculated by summing all the int representation of each character in the string.
   * Zero length string should return 0.
   *
   * @return hash of the string
   */
  @Override
  public int hashCode() {
    return IntStream.range(0, base.length).map(symbol -> (int) base[symbol]).sum();
  }

  /**
   * Converts StdString to java.lang.String. This is the only place where it is allowed to use
   * java.lang.String.
   *
   * @return java.lang.String representation of this string
   */
  @Override
  public String toString() {
    return new String(this.base);
  }

  /**
   * Create Character iterator. No specific requirements, classic Java iterator.
   *
   * @return Character iterator
   */
  @Override
  public Iterator<Character> iterator() {
    return new Iterator<>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return index < StdString1.this.length();
      }

      @Override
      public Character next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return StdString1.this.charAt(index++);
      }
    };
  }

  @Override
  public void forEach(final Consumer<? super Character> action) {
    for (var symbol : this) {
      action.accept(symbol);
    }
  }
}
