package com.capgemini.algorithmbattles.ballsapp.logic.model;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;

public class Board {

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
    public BoardCell evaluate(Player player) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 10; row++) {
            int i = 0;
            while (i <= 7) {
                if (board[row][i] == board[row][i + 1] && board[row][i + 1] == board[row][i + 2] && board[row][i].equals(player)) {
                    if (i>0 && i<7) {
                        if (board[row][i - 1].equals(player.getOther()) && board[row][i + 3].equals(player.getOther())) {
                            continue;
                        } else {
                            if (board[row][i - 1].equals(player.getOther())) {
                                return new BoardCell(row, i+3, null);
                            } else
                                return new BoardCell(row, i-1, null);
                        }
                    } else if (i == 0){
                        if (board[row][i + 3].toString().equals(player.getOther())) {
                            continue;
                        } else {
                            return new BoardCell(row, i+3, null);
                        }
                    } else if (i == 7){
                        if (board[row][i - 1].toString().equals(player.getOther())) {
                            continue;
                        } else {
                            return new BoardCell(row, i-1, null);
                        }
                    }
                }
                i++;
            }
        }


        // Checking for Columns for X or O victory.
        for (int col = 0; col < 10; col++) {
            int i = 0;
            while (i <= 7) {
                if (board[i][col] == board[i + 1][col] && board[i + 1][col] == board[i + 2][col] && board[i][col].equals(player)) {
                    if (i>0 && i<7) {
                        if (board[i - 1][col].equals(player.getOther()) && board[i + 3][col].equals(player.getOther())) {
                            continue;
                        } else {
                            if (board[i - 1][col].equals(player.getOther())) {
                                return new BoardCell(i+3, col, null);
                            } else
                                return new BoardCell( i-1, col, null);
                        }
                    } else if (i == 0){
                        if (board[i + 3][col].equals(player.getOther())) {
                            continue;
                        } else {
                            return new BoardCell( i+3, col, null);
                        }
                    } else if (i == 7){
                        if (board[i - 1][col].equals(player.getOther())) {
                            continue;
                        } else {
                            return new BoardCell(i-1, col, null);
                        }
                    }
                }
                i++;
            }
        }
        // Checking for diagonals going from top left corner to bottom right corner.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == board[row + 1][col + 1] && board[row + 1][col + 1] == board[row + 2][col + 2] && board[row][col].equals(player)) {
                    if(row > 0 && col > 0){
                        if (row < 7 && col < 7){
                            if (board[row-1][col-1].equals(player.getOther()) && board[row+3][col+3].equals(player.getOther())) {

                            }else{
                                if (board[row-1][col-1].equals(player.getOther())) {
                                    return new BoardCell(row+3, col+3, null);
                                } else
                                    return new BoardCell( row-1, col-1, null);
                            }
                        } else {
                            if (board[row - 1][col - 1].equals(player.getOther())) {

                            } else {
                                return new BoardCell( row-1, col-1, null);
                            }
                        }
                    } else {
                        if (board[row +3][col +3].equals(player.getOther())) {

                        } else {
                            return new BoardCell( row+3, col+3, null);
                        }
                    }
                }
            }

        }
        // Checking for diagonals going from top right corner to bottom left corner.
        for (int row = 0; row < 8; row++) {
            for (int col = 9; col >= 2; col--) {
                if (board[row][col] == board[row + 1][col - 1] && board[row + 1][col - 1] == board[row + 2][col - 2] && board[row][col].equals(player)) {
                    if(row > 0 && col < 9){
                        if (row < 7 && col > 2){
                            if (board[row-1][col+1].equals(player.getOther()) && board[row+3][col-3].equals(player.getOther())) {
                            }else{
                                if (board[row-1][col+1].equals(player.getOther())) {
                                    return new BoardCell(row+3, col-3, null);
                                } else
                                    return new BoardCell( row-1, col+1, null);
                            }
                        } else {
                            if (board[row - 1][col + 1].equals(player.getOther())) {

                            } else {
                                return new BoardCell( row-1, col+1, null);
                            }
                        }
                    } else {
                        if (board[row +3][col -3].equals(player.getOther())) {
                        } else {
                            return new BoardCell( row+3, col-3, null);
                        }
                    }
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
