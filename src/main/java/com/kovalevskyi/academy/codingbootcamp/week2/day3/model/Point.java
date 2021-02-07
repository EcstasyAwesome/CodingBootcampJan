package com.kovalevskyi.academy.codingbootcamp.week2.day3.model;

public class Point {

  private final int axisX;
  private final int axisY;
  private final Figure figure;

  public Point(final int axisX, final int axisY, final Figure figure) {
    this.axisX = axisX;
    this.axisY = axisY;
    this.figure = figure;
  }

  public int getAxisX() {
    return axisX;
  }

  public int getAxisY() {
    return axisY;
  }

  public Figure getFigure() {
    return figure;
  }
}
