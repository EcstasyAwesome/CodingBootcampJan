package com.kovalevskyi.academy.codingbootcamp.week2.day1;

import com.kovalevskyi.academy.codingbootcamp.week1.day1.StringUtils;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * w2d1 task.
 */
public class BoxGenerator {

  private final String[] arguments;
  private int width;
  private int height;
  private String wall;
  private String corner;

  /**
   * Init BoxGenerator and parse arguments.
   *
   * @param arguments some args
   */
  public BoxGenerator(final String[] arguments) {
    this.arguments = arguments;
    parseArguments();
  }

  /**
   * Main app logic.
   */
  public void run() {
    if (width > 0 && height > 0) {
      IntStream
          .range(0, height)
          .forEach(index -> {
            if (index == 0 || index == height - 1) {
              LineGenerator.generateLine(corner, corner, wall, width).forEach(System.out::print);
            } else {
              LineGenerator.generateLine(wall, wall, " ", width).forEach(System.out::print);
            }
            System.out.println();
          });
    }
  }

  /**
   * Start point of BoxGenerator.
   *
   * @param args - some args.
   */
  public static void main(String[] args) {
    new BoxGenerator(args).run();
  }

  private void parseArguments() {
    Objects.requireNonNull(arguments, "Input parameter should be not null!");
    if (arguments.length != 4) {
      System.out.printf("Please provide 4 input arguments, current amount: %d%n", arguments.length);
    } else {
      Stream
          .of(arguments)
          .forEach(entry -> {
            Objects.requireNonNull(entry, "Array's entry should be not null!");
            if (entry.length() != 1) {
              throw new IllegalArgumentException("Array's entry has invalid format!");
            }
          });
      width = StringUtils.toInt(arguments[0].toCharArray());
      height = StringUtils.toInt(arguments[1].toCharArray());
      wall = arguments[2];
      corner = arguments[3];
    }
  }

}
