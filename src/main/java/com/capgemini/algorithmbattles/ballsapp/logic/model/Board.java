package com.capgemini.algorithmbattles.ballsapp.logic.model;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;

import java.util.Random;

public class Board {
    private int numFilledCells = 0;
    private static final int SIZE = 10;
    private Player[][] board = new Player[SIZE][SIZE];

    public static final double FIVE_IN_A_ROW = Double.POSITIVE_INFINITY;
    public static final double STRAIGHT_FOUR_POINTS = 1000;
    public static final double FOURS_POINTS = 500;
    public static final double THREES_POINTS = 100;
    public static final double TWOS_POINTS = 5;
    public static final double ONES_POINTS = 1;

    private static boolean DEBUG = false;

    public int getNumFilledCells() {
        return numFilledCells;
    }

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


    public BoardCell attackMove(Player player) {
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
                if (board[i][col] == board[i + 1][col] && board[i][col] != null) {
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
                        if (row > 0) {
                            if (row < 8) {
                                if (col > 0) {
                                    if (col < 8) {
                                        if (board[row - 1][col - 1] == null)
                                            return new BoardCell(row - 1, col - 1, null);

                                        if (board[row + 2][col + 2] == null)
                                            return new BoardCell(row + 2, col + 2, null);
                                    } else {
                                        if (board[row - 1][col - 1] == null)
                                            return new BoardCell(row - 1, col - 1, null);
                                    }
                                } else {
                                    if (board[row + 2][col + 2] == null)
                                        return new BoardCell(row + 2, col + 2, null);
                                }
                            } else {
                                if (board[row - 1][col - 1] == null)
                                    return new BoardCell(row - 1, col - 1, null);
                            }
                        } else {
                            if (board[row + 2][col + 2] == null)
                                return new BoardCell(row + 2, col + 2, null);
                        }
                    }
                }
            }

        }

        // Checking for diagonals going from top right corner to bottom left corner.
        for (int row = 0; row < 9; row++) {
            for (int col = 9; col >= 1; col--) {
                if (board[row][col] == board[row + 1][col - 1] && board[row][col] != null) {
                    if (board[row][col].equals(player)) {
                        if (row > 0) {
                            if (row < 8) {
                                if (col < 9) {
                                    if (col > 1) {
                                        if (board[row + 2][col - 2] == null)
                                            return new BoardCell(row + 2, col - 2, null);
                                    } else {
                                        if (board[row - 1][col + 1] == null)
                                            return new BoardCell(row - 1, col + 1, null);
                                    }
                                } else {
                                    if (board[row + 2][col - 2] == null)
                                        return new BoardCell(row + 2, col - 2, null);
                                }
                            } else {
                                if (board[row - 1][col + 1] == null)
                                    return new BoardCell(row - 1, col + 1, null);
                            }
                        } else {
                            if (board[row + 2][col - 2] == null)
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
                        if (row > 0) {
                            if (row < 7) {
                                if (col > 0) {
                                    if (col < 7) {
                                        if (board[row - 1][col - 1] == null)
                                            return new BoardCell(row - 1, col - 1, null);

                                        if (board[row + 3][col + 3] == null)
                                            return new BoardCell(row + 3, col + 3, null);
                                    } else {
                                        if (board[row - 1][col - 1] == null)
                                            return new BoardCell(row - 1, col - 1, null);
                                    }
                                } else {
                                    if (board[row + 3][col + 3] == null)
                                        return new BoardCell(row + 3, col + 3, null);
                                }
                            } else {
                                if (board[row - 1][col - 1] == null)
                                    return new BoardCell(row - 1, col - 1, null);
                            }
                        } else {
                            if (board[row + 3][col + 3] == null)
                                return new BoardCell(row + 3, col + 3, null);
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
                        if (row > 0) {
                            if (row < 8) {
                                if (col < 9) {
                                    if (col > 2) {
                                        if (board[row + 3][col - 3] == null)
                                            return new BoardCell(row + 3, col - 3, null);
                                    } else {
                                        if (board[row - 1][col + 1] == null)
                                            return new BoardCell(row - 1, col + 1, null);
                                    }
                                } else {
                                    if (board[row + 3][col - 3] == null)
                                        return new BoardCell(row + 3, col - 3, null);
                                }
                            } else {
                                if (board[row - 1][col + 1] == null)
                                    return new BoardCell(row - 1, col + 1, null);
                            }
                        } else {
                            if (board[row + 3][col - 3] == null)
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

        if (sum == 0) {
            Random rand = new Random();
            int a = 0;
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


    private static double getPointsToAdd(int val) {

        switch (val) {

            case 1:

                return ONES_POINTS;

            case 2:

                return TWOS_POINTS;

            case 3:

                return THREES_POINTS;

            case 5:

                return FIVE_IN_A_ROW;

            default:

                return 0;

        }

    }


    /**
     * Gets the utility for a specified player in a specified state.
     *
     * @param player The player's state to analyze
     * @return The utility of the gamestate.
     */
    public double getGameState(Player player) {
        double[][] maxUtility = new double[SIZE][SIZE];

        Player enemy = player.getOther();
        double evaluation = 0.0;
        int boardLength = SIZE;
        int count;
        int lastEnemyEncounteredCol, lastEnemyEncounteredRow;
        int encounteredEnemy, encounteredEnemyY;


        for (int row = 0; row < boardLength; row++) {
            lastEnemyEncounteredCol = -1;
            lastEnemyEncounteredRow = -1;
            for (int col = 0; col < boardLength; col++) {

                if (board[row][col] == enemy) {
                    lastEnemyEncounteredCol = col;//keep track of the last encountered enemy
                    lastEnemyEncounteredRow = row;
                }


                //If we find the string contains the player
                if (board[row][col] == player) {


                    encounteredEnemy = -1;
                    //====================CHECK TO THE RIGHT====================
                    if (col <= boardLength - 5) {//to be sure there can actually be a 5-in-a-row to this direction

                        count = 1; //Sum of how many of our players we encounter in the next 4 spaces
                        for (int x = col + 1; x < col + 5; x++) {
                            if (board[row][x] == player) {
                                count++;
                            } else if (board[row][x] == enemy) {
                                encounteredEnemy = x;
                                break;
                            }
                        }

                        if (count < 3 || count == 5) {
                            evaluation += getPointsToAdd(count);
                            if (DEBUG)
                                System.out.println("[horiz]BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + getPointsToAdd(count));
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                                if (DEBUG)
                                    System.out.println("[horiz(1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                            } else if (lastEnemyEncounteredCol > -1) {//we encountered an enemy before seeing our player
                                if (col - 1 >= 0 && encounteredEnemy == col + 4) {//we have enough room to make a 4, check to the left one to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row][col - 1] != enemy) {
                                        evaluation += THREES_POINTS;
                                        if (DEBUG)
                                            System.out.println("[horiz](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                    }
                                } else if (col - 2 >= 0 && encounteredEnemy == col + 3) {//we are stuck on 3, check to the left 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                    if (DEBUG)
                                        System.out.println("[horiz](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                }
                            }
                        } else if (count == 4 && col - 1 < 0 && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            if (DEBUG)
                                System.out.println("[horiz](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (col + 5 >= boardLength || col - 1 < 0)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                            if (DEBUG) System.out.println("[horiz]BLOCKING ON EDGE!!!!!!");
                        } else { //check for the straight four
                            //String rowString = (board[row],col - 1, 6); //Create string representation to check for straight 4
                            Player[] rowSequence = new Player[];
                            for (int i = -1; i < 5; i++) {
                                rowSequence[i + 1] = board[row][col + i];
                            }
                            if (isStraightFour(rowSequence, player)) {//If it is a straight 4
                                if (DEBUG)
                                    System.out.println("[horiz](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + STRAIGHT_FOUR_POINTS);
                                evaluation += STRAIGHT_FOUR_POINTS;
                                //
                                //
                                //
                                // mark
                                //
                                //
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                                if (DEBUG)
                                    System.out.println("[horiz](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left
                                if (board[row][col - 1] != enemy) {
                                    evaluation += FOURS_POINTS;
                                    if (DEBUG)
                                        System.out.println("[horiz](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                                }
                            }
                        }

                    }//FINISH CHECKING TO THE RIGHT
                    maxUtility[row][col] = evaluation;
                    evaluation = 0;


                    encounteredEnemy = -1;
                    //====================CHECK BELOW====================
                    if (row <= boardLength - 5) {//to be sure there can actually be a 5-in-a-row to this direction

                        count = 1; //Sum of how many of our players we encounter in the next 4 spaces
                        for (int x = row + 1; x < row + 5; x++) {
                            if (board[x][col] == player) {
                                count++;
                            } else if (board[x][col] == enemy) {
                                encounteredEnemy = x;
                                break;
                            }
                        }

                        if (count < 3 || count == 5) {
                            evaluation += getPointsToAdd(count);
                            if (DEBUG)
                                System.out.println("[down]BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + getPointsToAdd(count));
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                                if (DEBUG)
                                    System.out.println("[down](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                            } else if (lastEnemyEncounteredRow > -1) {//we encountered an enemy before seeing our player
                                if (row - 1 >= 0 && encounteredEnemy == row + 4) {//we have enough room to make a 4, check above to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row - 1][col] != enemy) {
                                        evaluation += THREES_POINTS;
                                        if (DEBUG)
                                            System.out.println("[down](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                    }
                                } else if (row - 2 >= 0 && encounteredEnemy == row + 3) {//we are stuck on 3, check to the left 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                    if (DEBUG)
                                        System.out.println("[down](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                }
                            }
                        } else if (count == 4 && row - 1 < 0 && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            if (DEBUG)
                                System.out.println("[down](0)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (row + 5 >= boardLength || row - 1 < 0)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                            if (DEBUG) System.out.println("[down]BLOCKING ON EDGE!!!!!!");
                        } else { //check for the straight four
                            char[] newChars = new char[6];

                            for (int b = row - 1, i = 0; b < boardLength && b < row + 5; b++, i++) {
                                newChars[i] = board[b][col];
                            }
                            String rowString = new String(newChars);

                            if (isStraightFour(rowString, player)) {//If it is a straight 4
                                if (DEBUG)
                                    System.out.println("[down](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + STRAIGHT_FOUR_POINTS);
                                evaluation += STRAIGHT_FOUR_POINTS;
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                                if (DEBUG)
                                    System.out.println("[down](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left
                                if (board[row - 1][col] != enemy) {
                                    evaluation += FOURS_POINTS;
                                    if (DEBUG)
                                        System.out.println("[down](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                                }
                            }
                        }

                    }//FINISH CHECKING BELOW

                    if (evaluation > maxUtility[row][col]) {
                        maxUtility[row][col] = evaluation;
                    }
                    evaluation = 0;

                    encounteredEnemy = -1;
                    //====================CHECK ABOVE====================
                    if (row >= 4) {//to be sure there can actually be a 5-in-a-row to this direction

                        count = 1; //Sum of how many of our players we encounter in the next 4 spaces
                        for (int x = row - 1; x > row - 5; x--) {
                            if (board[x][col] == player) {

                                count++;
                            } else if (board[x][col] == enemy) {
                                encounteredEnemy = x;
                                break;
                            }
                        }

                        if (count < 3 || count == 5) {
                            evaluation += getPointsToAdd(count);
                            if (DEBUG)
                                System.out.println("[up]BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + getPointsToAdd(count));
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                                if (DEBUG)
                                    System.out.println("[up](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                            } else if (lastEnemyEncounteredRow > -1) {//we encountered an enemy before seeing our player
                                if (row + 1 < boardLength && encounteredEnemy == row - 4) {//we have enough room to make a 4, check upwards to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row + 1][col] != enemy) {
                                        evaluation += THREES_POINTS;
                                        if (DEBUG)
                                            System.out.println("[up](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                    }
                                } else if (row + 2 < boardLength && encounteredEnemy == row - 3) {//we are stuck on 3, check upwards 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                    if (DEBUG)
                                        System.out.println("[up](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                }
                            }
                        } else if (count == 4 && row + 1 >= boardLength && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            if (DEBUG)
                                System.out.println("[up](0)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (row - 5 < 0 || row + 1 >= boardLength)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                            if (DEBUG) System.out.println("[up]BLOCKING ON EDGE!!!!!!");
                        } else { //check for the straight four
                            char[] newChars = new char[6];

                            for (int b = row + 1, i = 0; b >= 0 && b > row - 5; b--, i++) {
                                newChars[i] = board[b][col];
                            }
                            String rowString = new String(newChars);

                            if (isStraightFour(rowString, player)) {//If it is a straight 4
                                if (DEBUG)
                                    System.out.println("[up](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + STRAIGHT_FOUR_POINTS);
                                evaluation += STRAIGHT_FOUR_POINTS;
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                                if (DEBUG)
                                    System.out.println("[up](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left
                                if (board[row + 1][col] != enemy) {
                                    evaluation += FOURS_POINTS;
                                    if (DEBUG)
                                        System.out.println("[up](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                                }
                            }
                        }

                    }//FINISH CHECKING ABOVE

                    if (evaluation > maxUtility[row][col]) {
                        maxUtility[row][col] = evaluation;
                    }
                    evaluation = 0;

                    encounteredEnemy = -1;
                    encounteredEnemyY = -1;
                    //====================CHECK BOTTOM-RIGHT DIAGONALLY====================
                    if (col + 4 < boardLength && row + 4 < boardLength) {//to be sure there can actually be a 5-in-a-row to this direction

                        count = 1; //Sum of how many of our players we encounter in the next 4 spaces
                        for (int x = row + 1, y = col + 1; x < row + 5 && y < col + 5; x++, y++) {
                            if (board[x][y] == player) {
                                count++;
                            } else if (board[x][y] == enemy) {
                                encounteredEnemy = x;
                                encounteredEnemyY = y;
                                break;
                            }
                        }

                        if (count < 3 || count == 5) {
                            evaluation += getPointsToAdd(count);
                            if (DEBUG)
                                System.out.println("[BR-D]BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + getPointsToAdd(count));
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                                if (DEBUG)
                                    System.out.println("[BR-D](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                            } else if (lastEnemyEncounteredRow > -1) {//we encountered an enemy before seeing our player
                                if ((row + 1 < boardLength && col - 1 >= 0) && (encounteredEnemy == row + 4 && encounteredEnemyY == col + 4)) {//we have enough room to make a 4, check upwards to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row + 1][col - 1] != enemy) {
                                        evaluation += THREES_POINTS;
                                        if (DEBUG)
                                            System.out.println("[BR-D](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                    }
                                } else if (row - 2 >= 0 && col - 2 >= 0 && (encounteredEnemy == row + 3 && encounteredEnemyY == col + 3)) {//we are stuck on 3, check upwards 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                    if (DEBUG)
                                        System.out.println("[BR-D](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                }
                            }
                        } else if (count == 4 && (col - 1 < 0 && row - 1 < 0) && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            if (DEBUG)
                                System.out.println("[BR-D](0)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (row + 5 >= boardLength || row - 1 < 0) && (col + 5 >= boardLength || col - 1 < 0)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                            if (DEBUG) System.out.println("[BR-D]BLOCKING ON EDGE!!!!!!");
                        } else { //check for the straight four
                            char[] newChars = new char[6];

                            for (int b = row - 1, c = col - 1, i = 0; b < boardLength && b < row + 5 && b >= 0 && c >= 0; b++, c++, i++) {
                                if (DEBUG) System.out.println(b + " " + c);
                                newChars[i] = board[b][c];
                            }
                            String rowString = new String(newChars);

                            if (isStraightFour(rowString, player)) {//If it is a straight 4
                                if (DEBUG)
                                    System.out.println("[BR-D](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + STRAIGHT_FOUR_POINTS);
                                evaluation += STRAIGHT_FOUR_POINTS;
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                                if (DEBUG)
                                    System.out.println("[BR-D](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left
                                if (row - 1 >= 0 && col - 1 >= 0 && board[row - 1][col - 1] != enemy) {
                                    evaluation += FOURS_POINTS;
                                    if (DEBUG)
                                        System.out.println("[BR-D](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                                }
                            }
                        }

                    }//FINISH BOTTOM RIGHT DIAGONAL

                    if (evaluation > maxUtility[row][col]) {
                        maxUtility[row][col] = evaluation;
                    }
                    evaluation = 0;

                    encounteredEnemy = -1;
                    encounteredEnemyY = -1;
                    //====================CHECK TOP-RIGHT DIAGONALLY====================
                    if (col + 4 < boardLength && row - 4 >= 0) {//to be sure there can actually be a 5-in-a-row to this direction

                        count = 1; //Sum of how many of our players we encounter in the next 4 spaces
                        for (int x = row - 1, y = col + 1; x > row - 5 && y < col + 5; x--, y++) {
                            if (board[x][y] == player) {
                                count++;
                            } else if (board[x][y] == enemy) {
                                encounteredEnemy = x;
                                encounteredEnemyY = y;
                                break;
                            }
                        }

                        if (count < 3 || count == 5) {
                            evaluation += getPointsToAdd(count);
                            if (DEBUG)
                                System.out.println("[TR-D]BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + getPointsToAdd(count));
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                                if (DEBUG)
                                    System.out.println("[TR-D](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                            } else if (lastEnemyEncounteredRow > -1) {//we encountered an enemy before seeing our player
                                if ((row - 1 >= 0 && col - 1 >= 0) && (encounteredEnemy == row - 4 && encounteredEnemyY == col + 4)) {//we have enough room to make a 4, check upwards to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row - 1][col - 1] != enemy) {
                                        evaluation += THREES_POINTS;
                                        if (DEBUG)
                                            System.out.println("[TR-D](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                    }
                                } else if (row + 2 < boardLength && col - 2 >= 0 && (encounteredEnemy == row - 3 && encounteredEnemyY == col + 3)) {//we are stuck on 3, check upwards 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                    if (DEBUG)
                                        System.out.println("[TR-D](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + THREES_POINTS);
                                }
                            }
                        } else if (count == 4 && (col - 1 < 0 && row + 1 >= boardLength) && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            if (DEBUG)
                                System.out.println("[TR-D](0)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (row - 5 < 0 || row + 1 >= boardLength) && (col + 5 >= boardLength || col - 1 < 0)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                            if (DEBUG) System.out.println("[TR-D]BLOCKING ON EDGE!!!!!!");
                        } else { //check for the straight four
                            char[] newChars = new char[6];

                            for (int b = row + 1, c = col - 1, i = 0; b < boardLength && b > row - 5 && c >= 0; b--, c++, i++) {
                                newChars[i] = board[b][c];
                            }
                            String rowString = new String(newChars);

                            if (isStraightFour(rowString, player)) {//If it is a straight 4
                                if (DEBUG)
                                    System.out.println("[TR-D](1)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + STRAIGHT_FOUR_POINTS);
                                evaluation += STRAIGHT_FOUR_POINTS;
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                                if (DEBUG)
                                    System.out.println("[TR-D](2)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left

                                if (board[row + 1][col - 1] != enemy) {
                                    evaluation += FOURS_POINTS;
                                    if (DEBUG)
                                        System.out.println("[TR-D](3)BOARD[" + row + "][" + col + "]: ADDED UTILITY VALUE OF: " + FOURS_POINTS);
                                }

                            }
                        }
                    }//FINISH TOP-RIGHT DIAGONAL SEARCH

                    if (evaluation > maxUtility[row][col]) {
                        maxUtility[row][col] = evaluation;
                    }

                }
            }//inner (column) loop
        }//outer (row) loop

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                evaluation += maxUtility[i][j];
            }
        }

        return evaluation;
    }

    private static boolean isStraightFour(Player[] sequence, Player player) {
        Player[] straightFour = new Player[]{null, player, player, player, player, null};
        // if (DEBUG) System.out.println("IsStraightFour? " + in.replaceAll(" ", "-"));
        return sequence.equals(straightFour);
    }


}
