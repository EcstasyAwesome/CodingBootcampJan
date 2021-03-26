package com.kovalevskyi.academy.codingbootcamp.week2.day2;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * w2d2 task.
 */
public class TimeProgressBar {

  private final String progressBarPattern;
  private final int[] seconds;
  private final int steps;
  private int step;
  private int leftTime;

  /**
   * Constructor.
   *
   * @param steps   amount of loading elements
   * @param seconds time values
   */
  public TimeProgressBar(final int steps, final int[] seconds) {
    if (steps < 1 || seconds.length == 0) {
      throw new IllegalArgumentException("Error in progress bar initialization");
    }
    this.steps = steps;
    this.seconds = generateArrayOfRandomSecondsBy(seconds, this.steps);
    this.leftTime = Arrays.stream(this.seconds).sum();
    this.progressBarPattern = "\r%3.0f%% [%-100s] %d/%d ETA: %tT";
    this.step = 0;
  }

  /**
   * Main logic of progress bar.
   *
   * @param stream output stream
   */
  public void printProgressBar(final PrintStream stream) {
    stream.print(prepareString(LocalTime.ofSecondOfDay(0)));
    while (step < steps) {
      try {
        TimeUnit.SECONDS.sleep(seconds[step]);
        stream.print(prepareString(LocalTime.ofSecondOfDay(leftTime -= seconds[step++])));
      } catch (InterruptedException exception) {
        exception.printStackTrace();
      }
    }
  }

  private String prepareString(final LocalTime leftTime) {
    return String.format(progressBarPattern,
        getProgress(),
        getProgressLine(),
        step,
        steps,
        leftTime);
  }

  private int[] generateArrayOfRandomSecondsBy(final int[] array, final int size) {
    final var random = new Random();
    return IntStream.range(0, size)
        .map(index -> array[random.nextInt(array.length)])
        .toArray();
  }

  private double getProgress() {
    return (100.0 / steps) * step;
  }

  private String getProgressLine() {
    var loadLine = String.format("%s>", "=".repeat(((int) getProgress())));
    return loadLine.length() <= steps ? loadLine : loadLine.substring(1);
  }
}