package com.kovalevskyi.academy.codingbootcamp.week0.day3;

/**
 * w0d3 task.
 */
public class Point implements Comparable<Point> {

  private final int coordinateX;
  private final int coordinateY;

  public Point(final int coordinateX, final int coordinateY) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
  }

  public int getX() {
    return coordinateX;
  }

  public int getY() {
    return coordinateY;
  }

  /**
   * Generates a new point whose coordinates are equal to the sum of the coordinates of the current
   * and incoming points. This method depend of getters.
   *
   * @param that — incoming point.
   * @return new point.
   */
  public Point sum(final Point that) {
    return new Point(that.getX() + this.getX(), that.getY() + this.getY());
  }

  public Point updateX(int newX) {
    return new Point(newX, this.getY());
  }

  public Point updateY(int newY) {
    return new Point(this.getX(), newY);
  }

  /**
   * The method calculates and returns the distance from the current point A (X1, Y1) to the
   * incoming point B (X2, Y2) using the formula: ((X1−X2)^2 + (Y1 − Y2)^2).
   *
   * @param that incoming Point.
   * @return the distance number.
   */
  public int distanceTo(Point that) {
    var distanceX = that.getX() - this.getX();
    var distanceY = that.getY() - this.getY();
    return distanceX * distanceX + distanceY * distanceY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point point = (Point) o;
    return coordinateX == point.coordinateX && coordinateY == point.coordinateY;
  }

  @Override
  public int hashCode() {
    return coordinateX + coordinateY;
  }

  @Override
  public String toString() {
    return String.format("Point{X: %d, Y: %d}", this.getX(), this.getY());
  }

  @Override
  public int compareTo(Point that) {
    return this.hashCode() - that.hashCode();
  }
}
