package com.kovalevskyi.academy.codingbootcamp.week1.day0;

import com.kovalevskyi.academy.codingbootcamp.week0.day3.Point;
import java.util.function.Function;

/**
 * w1d0 task.
 */
public class PointWithValue<T> extends Point {

  private final T value;

  public PointWithValue(int coordinateX, int coordinateY, T value) {
    super(coordinateX, coordinateY);
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public <R> PointWithValue<R> mapPoint(Function<T, R> mapFunction) {
    return new PointWithValue<>(getX(), getY(), mapFunction.apply(value));
  }

  @Override
  public String toString() {
    return String.format("PointWithValue{ X: %d, Y: %d, value: %s}", getX(), getY(), getValue());
  }
}
