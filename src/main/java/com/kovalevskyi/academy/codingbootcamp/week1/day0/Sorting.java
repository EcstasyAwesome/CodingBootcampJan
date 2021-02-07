package com.kovalevskyi.academy.codingbootcamp.week1.day0;

import java.util.Comparator;

/**
 * w1d0 task.
 */
public class Sorting {

  /**
   * Natural order sorting.
   *
   * @param target     - array of some objects
   * @param comparator - sorting logic
   * @param <T>        - type of objects
   */
  public static <T> void sort(T[] target, Comparator<T> comparator) {
    sortArray(target, comparator, false);
  }

  /**
   * Reversed order sorting.
   *
   * @param target     - array of some objects
   * @param comparator - sorting logic
   * @param <T>        - type of objects
   */
  public static <T> void sortReversedOrder(T[] target, Comparator<T> comparator) {
    sortArray(target, comparator, true);
  }

  private static <T> void sortArray(final T[] target, final Comparator<T> comparator,
      final boolean reservedOrder) {
    if (comparator == null) {
      throw new NullPointerException();
    }
    for (int current = 0; current < target.length; current++) {
      for (int search = current + 1; search < target.length; search++) {
        if (reservedOrder) {
          if (comparator.compare(target[current], target[search]) < 0) {
            swap(target, current, search);
          }
        } else {
          if (comparator.compare(target[current], target[search]) > 0) {
            swap(target, current, search);
          }
        }
      }
    }
  }

  private static <T> void swap(final T[] target, final int index1, final int index2) {
    T tmp = target[index1];
    target[index1] = target[index2];
    target[index2] = tmp;
  }
}
