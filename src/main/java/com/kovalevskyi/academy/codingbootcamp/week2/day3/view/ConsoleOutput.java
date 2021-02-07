package com.kovalevskyi.academy.codingbootcamp.week2.day3.view;

import com.kovalevskyi.academy.codingbootcamp.week2.day3.model.Board;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.model.Figure;
import java.io.PrintStream;
import java.util.stream.IntStream;

public class ConsoleOutput implements IOutput {

  private final PrintStream stream;

  public ConsoleOutput(PrintStream stream) {
    this.stream = stream;
  }

  @Override
  public void visualize(Board board) {
    for (var axisY : board.getFigures()) {
      linePrinter(axisY);
    }
  }

  @Override
  public void sendMessage(String string) {
    stream.println(string);
  }

  private void linePrinter(Figure[] figures) {
    var wall = '|';
    IntStream
        .range(0, figures.length)
        .forEach(index -> {
          if (index == 0) {
            stream.printf("%c%c", wall, figures[index].symbol);
          } else if (index == figures.length - 1) {
            stream.printf("%c%c%n", figures[index].symbol, wall);
          } else {
            stream.printf("%c%c%1$c", wall, figures[index].symbol);
          }
        });
  }
}
