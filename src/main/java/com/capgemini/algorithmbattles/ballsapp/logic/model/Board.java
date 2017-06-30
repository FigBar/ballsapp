package com.capgemini.algorithmbattles.ballsapp.logic.model;

public class Board {

	private static final int size = 10;
	private Player[][] board = new Player[size][size];

	public void placeMove(BoardCell move) {
		board[move.getX()][move.getY()] = move.getPlayer();
	}

	public BoardCell getFirstEmptyCell() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == null) {
					return new BoardCell(i, j, null);
				}
			}
		}
		return null;

	}

}
