package com.capgemini.algorithmbattles.ballsapp.logic.model;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;

public class Board {

  private static final int SIZE = 10;
  private Player[][] board = new Player[SIZE][SIZE];

  public void placeMove(BoardCell move) {
    board[move.getX()][move.getY()] = move.getPlayer();
  }

  public BoardCell getFirstEmptyCell() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j] == null) {
          return new BoardCell(i, j, null);
        }
      }
    }
    return null;
  }

  public boolean isGameFinished() {
    // TODO: Please implement it.
    return false;
  }

  public void printBoard() {
    BoardDrawer.drawBoard(this.board);
  }

}
