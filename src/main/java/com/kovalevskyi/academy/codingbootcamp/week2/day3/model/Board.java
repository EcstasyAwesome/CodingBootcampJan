package com.kovalevskyi.academy.codingbootcamp.week2.day3.model;

public class Board {

  private final Figure[][] figures;

  public Board() {
    figures = new Figure[3][3];
    clearBoard();
  }

  public int getWidth() {
    return figures[0].length;
  }

  public int getHeight() {
    return figures.length;
  }

  public Figure[][] getFigures() {
    return figures.clone();
  }

  public boolean putFigureOnBoard(final Point point) {
    if (figures[point.getAxisY()][point.getAxisX()] == Figure.NONE) {
      figures[point.getAxisY()][point.getAxisX()] = point.getFigure();
      return true;
    }
    return false;
  }

  public void clearBoard() {
    for (var axisY = 0; axisY < getHeight(); axisY++) {
      for (var axisX = 0; axisX < getWidth(); axisX++) {
        figures[axisY][axisX] = Figure.NONE;
      }
    }
  }
}
