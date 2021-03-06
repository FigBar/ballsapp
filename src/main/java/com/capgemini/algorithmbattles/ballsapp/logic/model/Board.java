package com.capgemini.algorithmbattles.ballsapp.logic.model;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;

public class Board {
    public int i;
    private static final int SIZE = 10;
    private Player[][] board = new Player[SIZE][SIZE];

    public void placeMove(BoardCell move) {
        board[move.getX()][move.getY()] = move.getPlayer();
    }

    public void remove(int x, int y){
        board[x][y] = null;
    }

    public Player getPlayerAt(int x, int y){
        return board[x][y];
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

    public Player[][] getBoard() {
        return board;
    }

    @SuppressWarnings("Duplicates")
    public int evaluate(Player player) {
        String which = player.toString();
        // Checking for Rows for X or O victory.
        int i = 0;
        for (int row = 0; row < 10; row++) {
            i = 0;
            while (i <= 5) {
                if (board[row][i] == board[row][i + 1] &&
                        board[row][i + 1] == board[row][i + 2] && board[row][i + 2] == board[row][i + 3] && board[row][i + 3] == board[row][i + 4]) {

                    if (board[row][i] == null) {
                        i++;
                        continue;

                    } else if (board[row][i].toString().equals(which))
                        return +10;
                    else
                        return -10;

                }
                i++;
            }
        }

        i = 0;

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 10; col++) {
            i = 0;
            while (i <= 5) {
                if (board[i][col] == board[i + 1][col] && board[i + 1][col] == board[i + 2][col] &&
                        board[i + 2][col] == board[i + 3][col] && board[i + 3][col] == board[i + 4][col]) {

                    if (board[i][col] == null) {
                        i++;
                        continue;
                    } else if (board[i][col].toString().equals(which))
                        return +10;
                    else
                        return -10;
                }
                i++;

            }
        }
        // Checking for diagonals going from top left corner to bottom right corner.
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board[row][col] == board[row + 1][col + 1] && board[row + 1][col + 1] == board[row + 2][col + 2] && board[row + 2][col + 2] == board[row + 3][col + 3] &&
                        board[row + 3][col + 3] == board[row + 4][col + 4]) {

                    if (board[row][col] == null) {
                    } else if (board[row][col].toString().equals(which))
                        return +10;
                    else
                        return -10;
                }
            }

        }
        // Checking for diagonals going from top right corner to bottom left corner.
        for (int row = 0; row < 6; row++) {
            for (int col = 9; col >= 4; col--) {
                if (board[row][col] == board[row + 1][col - 1] && board[row + 1][col - 1] == board[row + 2][col - 2] && board[row + 2][col - 2] == board[row + 3][col - 3] &&
                        board[row + 3][col - 3] == board[row + 4][col - 4]) {

                    if (board[row][col] == null) {
                    } else if (board[row][col].toString().equals(which))
                        return +10;
                    else
                        return -10;
                }
            }

        }

        return 0;
    }

    public boolean isGameFinished() {
        // TODO: Please implement it.
        return false;
    }


    public void printBoard() {
        BoardDrawer.drawBoard(this.board);
    }

}
