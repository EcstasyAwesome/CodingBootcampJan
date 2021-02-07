package com.kovalevskyi.academy.codingbootcamp.week2.day3.controller;

import com.kovalevskyi.academy.codingbootcamp.week2.day3.model.Board;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.model.Figure;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.model.Player;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.model.Point;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.view.IInput;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.view.IOutput;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Engine {

  private final Board board;
  private final IInput input;
  private final IOutput output;
  private final Pattern pattern;
  private Player playerOne;
  private Player playerTwo;

  public Engine(final Board board, final IInput input, final IOutput output) {
    this.board = board;
    this.input = input;
    this.output = output;
    var regex = String.format("^(?<X>[1-%d]).(?<Y>[1-%d])$", board.getWidth(), board.getHeight());
    pattern = Pattern.compile(regex);
  }

  public void run() {
    do {
      init();
      var playerSwitch = false;
      var steps = 0;
      while (steps++ <= 9) {
        final var currentPlayer = playerSwitch ? playerTwo : playerOne;
        move(currentPlayer);
        if (isWinner(currentPlayer)) {
          output.sendMessage(String.format("%s won! Congratulations!!!", currentPlayer.getName()));
          break;
        }
        if (steps == 9) {
          output.sendMessage("No winners!");
          break;
        }
        playerSwitch = !playerSwitch;
      }
    } while (playAgain());
  }

  private String getPlayerName(final String message) {
    final var error = "Input is empty! Try again!";
    output.sendMessage(message);
    var name = input.ask();
    while (name.isEmpty()) {
      output.sendMessage(error);
      name = input.ask();
    }
    return name;
  }

  private void init() {
    output.sendMessage("Welcome to XO game!");
    playerOne = new Player(getPlayerName("Player 1 name:"), Figure.X);
    playerTwo = new Player(getPlayerName("Player 2 name:"), Figure.O);
    output.sendMessage("Let's start! Have fun!");
    output.visualize(board);
  }

  private void move(final Player player) {
    var valid = false;
    do {
      output.sendMessage(String.format("%s's move:", player.getName()));
      final var coordinates = this.input.ask();
      final var matcher = pattern.matcher(coordinates);
      final var validInput = matcher.find();
      if (validInput) {
        var point = new Point(
            Integer.parseInt(matcher.group("X")) - 1,
            board.getHeight() - Integer.parseInt(matcher.group("Y")),
            player.getFigure());
        valid = board.putFigureOnBoard(point);
        if (!valid) {
          output.sendMessage("This coordinate in unavailable!");
        }
      } else {
        output.sendMessage("Wrong coordinates input!");
      }
      output.visualize(board);
    } while (!valid);
  }

  private boolean isWinner(final Player player) {
    final Function<Figure, Boolean> function = (x) -> player.getFigure() == x;
    for (var axisY : board.getFigures()) {
      var result = Stream.of(axisY).filter(function::apply).count();
      if (result == 3) {
        return true;
      }
    }
    for (var index = 0; index < board.getWidth(); index++) {
      var height = 0;
      var result = Stream.of(
          board.getFigures()[height++][index],
          board.getFigures()[height++][index],
          board.getFigures()[height][index])
          .filter(function::apply)
          .count();
      if (result == 3) {
        return true;
      }
    }
    var diagonal1 = Stream.of(
        board.getFigures()[0][0],
        board.getFigures()[1][1],
        board.getFigures()[2][2])
        .filter(function::apply)
        .count();
    if (diagonal1 == 3) {
      return true;
    }
    var diagonal2 = Stream.of(
        board.getFigures()[0][2],
        board.getFigures()[1][1],
        board.getFigures()[2][0])
        .filter(function::apply)
        .count();
    return diagonal2 == 3;
  }

  private boolean playAgain() {
    board.clearBoard();
    output.sendMessage("Do you want to play again? (yes/no)");
    final var error = "Input is empty! Try again!";
    var answer = input.ask();
    while (answer.isEmpty()) {
      output.sendMessage(error);
      answer = input.ask();
    }
    return answer.equals("yes");
  }
}
