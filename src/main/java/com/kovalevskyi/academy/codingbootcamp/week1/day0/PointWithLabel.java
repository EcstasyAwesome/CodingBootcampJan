package com.kovalevskyi.academy.codingbootcamp.week1.day0;

import com.kovalevskyi.academy.codingbootcamp.week0.day3.Point;

/**
 * w1d0 task.
 */
public class PointWithLabel extends PointWithValue<String> {

  public PointWithLabel(int coordinateX, int coordinateY, String label) {
    super(coordinateX, coordinateY, label);
  }

  public String getLabel() {
    return super.getValue();
  }

  @Override
  public int compareTo(Point that) {
    if (that instanceof PointWithLabel) {
      return this.getLabel().compareTo(((PointWithLabel) that).getLabel());
    }
    return super.compareTo(that);
  }
}
