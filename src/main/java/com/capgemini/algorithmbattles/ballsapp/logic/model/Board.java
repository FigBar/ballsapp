package com.capgemini.algorithmbattles.ballsapp.logic.model;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;
import java.util.Random;
public class Board {
    private int numFilledCells = 0;
    private static final int SIZE = 10;
    private Player[][] board = new Player[SIZE][SIZE];

    public void placeMove(BoardCell move) {
        board[move.getX()][move.getY()] = move.getPlayer();
        numFilledCells++;
    }

    public void remove(int x, int y) {
        if (board[x][y] != null) {
            board[x][y] = null;
            numFilledCells--;
        }
    }

    public Player getPlayerAt(int x, int y) {
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



    public BoardCell attackMove(Player player){
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 10; row++) {
            int i = 0;
            while (i <= 8) {
                if (board[row][i] == board[row][i + 1] && board[row][i] != null) {
                    if (board[row][i].equals(player)) {
                        if (i > 0) {
                            if (i < 8) {
                                if (board[row][i - 1] == null)
                                    return new BoardCell(row, i - 1, null);
                                if (board[row][i + 2] == null) {
                                    return new BoardCell(row, i + 2, null);
                                }
                            } else {
                                if (board[row][i - 1] == null)
                                    return new BoardCell(row, i - 1, null);
                            }
                        } else {
                            if (board[row][i + 2] == null) {
                                return new BoardCell(row, i + 2, null);
                            }
                        }
                    }
                }
                i++;
            }
        }


        // Checking for Columns for X or O victory.
        for (int col = 0; col < 10; col++) {
            int i = 0;
            while (i <= 8) {
                if (board[i][col] == board[i + 1][col]  && board[i][col] != null) {
                    if (board[i][col].equals(player)) {
                        if (i > 0) {
                            if (i < 8) {
                                if (board[i - 1][col] == null)
                                    return new BoardCell(i - 1, col, null);
                                if (board[i + 2][col] == null) {
                                    return new BoardCell(i + 2, col, null);
                                }
                            } else {
                                if (board[i - 1][col] == null)
                                    return new BoardCell(i - 1, col, null);
                            }
                        } else {
                            if (board[i + 2][col] == null) {
                                return new BoardCell(i + 2, col, null);
                            }
                        }
                    }
                }
                i++;
            }
        }
        // Checking for diagonals going from top left corner to bottom right corner.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == board[row + 1][col + 1] && board[row][col] != null) {
                    if (board[row][col].equals(player)) {
                        if (row > 0){
                            if (row < 8){
                                if (col > 0){
                                    if (col < 8){
                                        if (board[row-1][col-1] == null)
                                            return new BoardCell(row - 1, col - 1, null);

                                        if (board[row+2][col+2] == null)
                                            return new BoardCell(row +2, col +2, null);
                                    } else {
                                        if (board[row-1][col-1] == null)
                                            return new BoardCell(row - 1, col - 1, null);
                                    }
                                } else {
                                    if (board[row+2][col+2] == null)
                                        return new BoardCell(row +2, col +2, null);
                                }
                            } else {
                                if (board[row-1][col-1] == null)
                                    return new BoardCell(row - 1, col - 1, null);
                            }
                        } else {
                            if (board[row+2][col+2] == null)
                                return new BoardCell(row +2, col +2, null);
                        }
                    }
                }
            }

        }

        // Checking for diagonals going from top right corner to bottom left corner.
        for (int row = 0; row < 9; row++) {
            for (int col = 9; col >= 1; col--) {
                if (board[row][col] == board[row + 1][col - 1]&& board[row][col] != null) {
                    if (board[row][col].equals(player)) {
                        if (row > 0){
                            if (row < 8){
                                if (col < 9){
                                    if (col > 1){
                                        if (board[row+2][col-2] == null)
                                            return new BoardCell(row + 2, col - 2, null);
                                    } else {
                                        if (board[row-1][col+1] == null)
                                            return new BoardCell(row - 1, col + 1, null);
                                    }
                                } else {
                                    if (board[row+2][col-2] == null)
                                        return new BoardCell(row + 2, col - 2, null);
                                }
                            } else {
                                if (board[row-1][col+1] == null)
                                    return new BoardCell(row - 1, col + 1, null);
                            }
                        }else{
                            if (board[row+2][col-2] == null)
                                return new BoardCell(row + 2, col - 2, null);
                        }
                    }
                }
            }
        }
        return null;
    }


    public BoardCell evaluate2(Player player) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 10; row++) {
            int i = 0;
            while (i <= 7) {
                if (board[row][i] == board[row][i + 1] && board[row][i + 1] == board[row][i + 2] && board[row][i] != null) {
                    if (board[row][i].equals(player)) {
                        if (i > 0) {
                            if (i < 7) {
                                if (board[row][i - 1] == null)
                                    return new BoardCell(row, i - 1, null);
                                if (board[row][i + 3] == null) {
                                    return new BoardCell(row, i + 3, null);
                                }
                            } else {
                                if (board[row][i - 1] == null)
                                    return new BoardCell(row, i - 1, null);
                            }
                        } else {
                            if (board[row][i + 3] == null) {
                                return new BoardCell(row, i + 3, null);
                            }
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
                if (board[i][col] == board[i + 1][col] && board[i + 1][col] == board[i + 2][col] && board[i][col] != null) {
                    if (board[i][col].equals(player)) {
                        if (i > 0) {
                            if (i < 7) {
                                if (board[i - 1][col] == null)
                                    return new BoardCell(i - 1, col, null);
                                if (board[i + 3][col] == null) {
                                    return new BoardCell(i + 3, col, null);
                                }
                            } else {
                                if (board[i - 1][col] == null)
                                    return new BoardCell(i - 1, col, null);
                            }
                        } else {
                            if (board[i + 3][col] == null) {
                                return new BoardCell(i + 3, col, null);
                            }
                        }
                    }
                }
                i++;
            }
        }
        // Checking for diagonals going from top left corner to bottom right corner.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == board[row + 1][col + 1] && board[row + 1][col + 1] == board[row + 2][col + 2] && board[row][col] != null) {
                    if (board[row][col].equals(player)) {
                        if (row > 0){
                            if (row < 7){
                                if (col > 0){
                                    if (col < 7){
                                        if (board[row-1][col-1] == null)
                                            return new BoardCell(row - 1, col - 1, null);

                                        if (board[row+3][col+3] == null)
                                            return new BoardCell(row +3, col +3, null);
                                    } else {
                                        if (board[row-1][col-1] == null)
                                            return new BoardCell(row - 1, col - 1, null);
                                    }
                                } else {
                                    if (board[row+3][col+3] == null)
                                        return new BoardCell(row +3, col +3, null);
                                }
                            } else {
                                if (board[row-1][col-1] == null)
                                    return new BoardCell(row - 1, col - 1, null);
                            }
                        } else {
                            if (board[row+3][col+3] == null)
                                return new BoardCell(row +3, col +3, null);
                        }
                    }
                }
            }

        }
        // Checking for diagonals going from top right corner to bottom left corner.
        for (int row = 0; row < 8; row++) {
            for (int col = 9; col >= 2; col--) {
                if (board[row][col] == board[row + 1][col - 1] && board[row + 1][col - 1] == board[row + 2][col - 2] && board[row][col] != null) {
                    if (board[row][col].equals(player)) {
                        if (row > 0){
                            if (row < 8){
                                if (col < 9){
                                    if (col > 2){
                                        if (board[row+3][col-3] == null)
                                            return new BoardCell(row + 3, col - 3, null);
                                    } else {
                                        if (board[row-1][col+1] == null)
                                            return new BoardCell(row - 1, col + 1, null);
                                    }
                                } else {
                                    if (board[row+3][col-3] == null)
                                        return new BoardCell(row + 3, col - 3, null);
                                }
                            } else {
                                if (board[row-1][col+1] == null)
                                    return new BoardCell(row - 1, col + 1, null);
                            }
                        }else{
                            if (board[row+3][col-3] == null)
                                return new BoardCell(row + 3, col - 3, null);
                        }
                    }
                }
            }
        }

        return null;
    }


    public int scoreGameState(Player player) {
        int sum = 0;

//        for (int row = 0; row < 10; row++) {
//            int i = 0;
//            while (i <= 7) {
//                if (board[row][i] == board[row][i + 1] && board[row][i + 1] == board[row][i + 2] && board[row][i] != null) {
//                    if (board[row][i].equals(player)) {
//                        if (i > 0 && i < 7) {
//                            //@_xxx_@
//                            if (board[row][i - 1] == null && board[row][i + 3] == null) {
//                                sum = sum+ 50;
//                                continue;
//                            }
//                            else {
//                                if (board[row][i - 1] != null && board[row][i + 3] == null) {
//                                    //@oxxx_@
//                                    if (board[row][i - 1].equals(player.getOther())) {
//                                        sum = sum +5;
//                                        //return new BoardCell(row, i + 3, null);
//                                    }
//                                    //@xxxx_@
//                                    else
//                                        sum = sum + 50;
//                                    //return new BoardCell(row, i - 1, null);
//                                }
//                                if (board[row][i - 1] == null && board[row][i + 3] != null) {
//                                    //@_xxxo@
//                                    if (board[row][i - 1].equals(player.getOther())) {
//                                        sum = sum +5;
//                                        //return new BoardCell(row, i + 3, null);
//                                    }
//                                    //@xxxx_@
//                                    else
//                                        sum = sum + 40;
//                                    //return new BoardCell(row, i - 1, null);
//                                }
//                            }
//                        }
//                        else if (i == 0) {
//                            //xxx?@@
//                            if (board[row][i + 3] != null) {
//                                //xxxo@@
//                                if (board[row][i + 3].toString().equals(player.getOther())) {
//                                    continue;
//                                }
//                                //xxxx@@
//                                else {
//                                    //xxxx_@
//                                    if(board[row][i+4] ==null)
//                                        sum = sum + 50;
//                                    //return new BoardCell(row, i + 3, null);
//                                }
//                            }
//                        } else if (i == 7) {
//                            //@@@?xxx
//                            if (board[row][i - 1] != null) {
//                                //@@@oxxx
//                                if (board[row][i - 1].toString().equals(player.getOther())) {
//                                    continue;
//                                }
//                                //@@@xxx
//                                else {
//                                    if(board[row][i-2] == null)
//                                        sum = sum+50;
//                                    //return new BoardCell(row, i - 1, null);
//                                }
//                            }
//                        }
//                    }
//                }
//                i++;
//            }
//        }

        if(sum == 0) {
            Random rand = new Random();
            int a=0;
            while (a == 0) {
                a = rand.nextInt(2) - 1;
            }
            sum = a * (rand.nextInt(998) + 1);
        }


        return sum;


//        // Checking for Columns for X or O victory.
//        for (int col = 0; col < 10; col++) {
//            int i = 0;
//            while (i <= 7) {
//                if (board[i][col] == board[i + 1][col] && board[i + 1][col] == board[i + 2][col] && board[i][col] != null) {
//                    if (board[i][col].equals(player)) {
//                        if (i > 0 && i < 7) {
//                            if (board[i - 1][col] != null && board[i + 3][col] != null) {
//                                continue;
//                            } else {
//                                if (board[i - 1][col] != null) {
//                                    if (board[i - 1][col].equals(player.getOther())) {
//                                        //return new BoardCell(i + 3, col, null);
//                                    } else
//                                        //return new BoardCell(i - 1, col, null);
//                                }
//                            }
//                        } else if (i == 0) {
//                            if (board[i + 3][col] != null) {
//                                if (board[i + 3][col].equals(player.getOther())) {
//                                    continue;
//                                } else {
//                                   // return new BoardCell(i + 3, col, null);
//                                }
//                            }
//                        } else if (i == 7) {
//                            if (board[i - 1][col] != null) {
//                                if (board[i - 1][col].equals(player.getOther())) {
//                                    continue;
//                                } else {
//                                    //return new BoardCell(i - 1, col, null);
//                                }
//                            }
//                        }
//                    }
//                }
//                i++;
//            }
//        }
//        // Checking for diagonals going from top left corner to bottom right corner.
//        for (int row = 0; row < 8; row++) {
//            for (int col = 0; col < 8; col++) {
//                if (board[row][col] != null) {
//                    if (board[row][col] == board[row + 1][col + 1] && board[row + 1][col + 1] == board[row + 2][col + 2] && board[row][col] != null) {
//                        if (row > 0 && col > 0) {
//                            if (row < 7 && col < 7) {
//                                if (board[row - 1][col - 1] != null && board[row + 3][col + 3] != null) {
//
//                                } else {
//                                    if (board[row - 1][col - 1] != null) {
//                                        if (board[row - 1][col - 1].equals(player.getOther())) {
//                                           // return new BoardCell(row + 3, col + 3, null);
//                                        } else
//                                           // return new BoardCell(row - 1, col - 1, null);
//                                    }
//                                }
//                            } else {
//                                if (board[row - 1][col - 1] != null) {
//                                    if (board[row - 1][col - 1].equals(player.getOther())) {
//
//                                    } else {
//                                       // return new BoardCell(row - 1, col - 1, null);
//                                    }
//                                }
//                            }
//                        } else {
//                            if (board[row + 3][col + 3] != null) {
//                                if (board[row + 3][col + 3].equals(player.getOther())) {
//
//                                } else {
//                                  //  return new BoardCell(row + 3, col + 3, null);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//        // Checking for diagonals going from top right corner to bottom left corner.
//        for (int row = 0; row < 8; row++) {
//            for (int col = 9; col >= 2; col--) {
//                if (board[row][col] != null) {
//                    if (board[row][col] == board[row + 1][col - 1] && board[row + 1][col - 1] == board[row + 2][col - 2] && board[row][col] != null) {
//                        if (row > 0 && col < 9) {
//                            if (row < 7 && col > 2) {
//                                if (board[row - 1][col + 1] != null && board[row + 3][col - 3] != null) {
//                                } else {
//                                    if (board[row - 1][col + 1] != null) {
//                                        if (board[row - 1][col + 1].equals(player.getOther())) {
//                                          //  return new BoardCell(row + 3, col - 3, null);
//                                        } else
//                                           // return new BoardCell(row - 1, col + 1, null);
//                                    }
//                                }
//                            } else {
//                                if (board[row - 1][col + 1] != null) {
//                                    if (board[row - 1][col + 1].equals(player.getOther())) {
//
//                                    } else {
//                                       // return new BoardCell(row - 1, col + 1, null);
//                                    }
//                                }
//                            }
//                        } else {
//                            if (board[row + 3][col - 3] != null) {
//                                if (board[row + 3][col - 3].equals(player.getOther())) {
//                                } else {
//                                    //return new BoardCell(row + 3, col - 3, null);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//            while(sum == 0) {
//                Random rand = new Random();
//                sum = rand.nextInt(2) - 1;
//            }
//
//        return sum;
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
                        return +1000;
                    else
                        return -1000;
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
                        return +1000;
                    else
                        return -1000;
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
                        return +1000;
                    else
                        return -1000;
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
                        return +1000;
                    else
                        return -1000;
                }
            }
        }
        return 0;
    }

    public boolean isGameFinished() {
        // TODO: Please implement it.
        return false;
    }

    public boolean isFull() {
        return numFilledCells == SIZE * SIZE;
    }

    public void printBoard() {
        BoardDrawer.drawBoard(this.board);
    }

}
