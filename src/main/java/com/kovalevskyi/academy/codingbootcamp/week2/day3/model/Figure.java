package com.kovalevskyi.academy.codingbootcamp.week2.day3.model;

public enum Figure {

  X('X'),
  O('O'),
  NONE(' ');

  public final char symbol;

  Figure(final char symbol) {
    this.symbol = symbol;
  }
}
