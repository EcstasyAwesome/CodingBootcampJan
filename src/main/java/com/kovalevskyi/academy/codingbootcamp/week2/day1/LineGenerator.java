package com.kovalevskyi.academy.codingbootcamp.week2.day1;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Class helper for w2d1 task.
 */
public class LineGenerator {

  /**
   * Generate some stream of input elements.
   *
   * @param cornerLeft  left corner symbol
   * @param cornerRight right corner symbol
   * @param fill        middle symbols
   * @param width       total with of stream elements (like string length)
   * @param <T>         stream elements type
   * @return stream of some elements
   */
  public static <T> Stream<T> generateLine(
      final T cornerLeft,
      final T cornerRight,
      final T fill,
      final int width) {
    var line = Stream.<T>builder();
    IntStream
        .range(0, width)
        .forEach(index -> {
          if (index == 0) {
            line.add(cornerLeft);
          } else if (index == width - 1) {
            line.add(cornerRight);
          } else {
            line.add(fill);
          }
        });
    return line.build();
  }

}