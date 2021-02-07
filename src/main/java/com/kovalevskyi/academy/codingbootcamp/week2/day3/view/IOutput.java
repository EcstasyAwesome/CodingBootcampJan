package com.kovalevskyi.academy.codingbootcamp.week2.day3.view;

import com.kovalevskyi.academy.codingbootcamp.week2.day3.model.Board;

public interface IOutput {

  void visualize(Board board);

  void sendMessage(String string);

}
