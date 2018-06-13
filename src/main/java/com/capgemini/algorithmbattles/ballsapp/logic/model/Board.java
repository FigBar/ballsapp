package com.capgemini.algorithmbattles.ballsapp.logic.model;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;
import com.capgemini.algorithmbattles.ballsapp.solution.ThreatUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Board {
    private int numFilledCells = 0;
    private static final int SIZE = 10;
    private Player[][] board = new Player[SIZE][SIZE];
    // private Stack<BoardCell> moves;
 /*   private Player currentPlayer;
    private Player startingPlayer;*/

    public static final double FIVE_IN_A_ROW = Double.POSITIVE_INFINITY - 1;
    public static final double STRAIGHT_FOUR_POINTS = 1000;
    public static final double FOURS_POINTS = 500;
    public static final double THREES_POINTS = 100;
    public static final double TWOS_POINTS = 5;
    public static final double ONES_POINTS = 1;

    public ThreatUtils patterns = new ThreatUtils();

    public int getNumFilledCells() {
        return numFilledCells;
    }

    /*public void setPlayer(Player player) {
        currentPlayer = player;
        startingPlayer = player;
    }*/

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

    /*public List<BoardCell> getMoves() {
        return new ArrayList(moves);
    }

    public BoardCell getLastMove() {
        return !moves.isEmpty() ? moves.peek() : null;
    }*/

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

    public double evaluate(Player player) {
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
                        return FIVE_IN_A_ROW;
                    else
                        return -FIVE_IN_A_ROW;
                }
                i++;
            }
        }


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
                        return FIVE_IN_A_ROW;
                    else
                        return -FIVE_IN_A_ROW;
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
                        return FIVE_IN_A_ROW;
                    else
                        return -FIVE_IN_A_ROW;
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
                        return FIVE_IN_A_ROW;
                    else
                        return -FIVE_IN_A_ROW;
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
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                            } else if (lastEnemyEncounteredCol > -1) {//we encountered an enemy before seeing our player
                                if (col - 1 >= 0 && encounteredEnemy == col + 4) {//we have enough room to make a 4, check to the left one to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row][col - 1] != enemy) {
                                        evaluation += THREES_POINTS;
                                    }
                                } else if (col - 2 >= 0 && encounteredEnemy == col + 3) {//we are stuck on 3, check to the left 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                }
                            }
                        } else if (count == 4 && col - 1 < 0 && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (col + 5 >= boardLength || col - 1 < 0)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                        } else { //check for the straight four
                            //String rowString = (board[row],col - 1, 6); //Create string representation to check for straight 4
                            Player[] rowSequence = new Player[6];
                            for (int i = -1; i < 5; i++) {
                                rowSequence[i + 1] = board[row][col + i];
                            }
                            if (isStraightFour(rowSequence, player)) {//If it is a straight 4 of our player
                                evaluation += STRAIGHT_FOUR_POINTS;
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left
                                if (board[row][col - 1] != enemy) {
                                    evaluation += FOURS_POINTS;
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
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                            } else if (lastEnemyEncounteredRow > -1) {//we encountered an enemy before seeing our player
                                if (row - 1 >= 0 && encounteredEnemy == row + 4) {//we have enough room to make a 4, check above to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row - 1][col] != enemy) {
                                        evaluation += THREES_POINTS;
                                    }
                                } else if (row - 2 >= 0 && encounteredEnemy == row + 3) {//we are stuck on 3, check to the left 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                }
                            }
                        } else if (count == 4 && row - 1 < 0 && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (row + 5 >= boardLength || row - 1 < 0)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                        } else { //check for the straight four
                            Player[] rowSequence = new Player[6];
                            for (int b = row - 1, i = 0; b < boardLength && b < row + 5; b++, i++) {
                                rowSequence[i] = board[b][col];
                            }

                            if (isStraightFour(rowSequence, player)) {//If it is a straight 4
                                evaluation += STRAIGHT_FOUR_POINTS;
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left
                                if (board[row - 1][col] != enemy) {
                                    evaluation += FOURS_POINTS;
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
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                            } else if (lastEnemyEncounteredRow > -1) {//we encountered an enemy before seeing our player
                                if (row + 1 < boardLength && encounteredEnemy == row - 4) {//we have enough room to make a 4, check upwards to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row + 1][col] != enemy) {
                                        evaluation += THREES_POINTS;
                                    }
                                } else if (row + 2 < boardLength && encounteredEnemy == row - 3) {//we are stuck on 3, check upwards 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                }
                            }
                        } else if (count == 4 && row + 1 >= boardLength && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (row - 5 < 0 || row + 1 >= boardLength)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                        } else { //check for the straight four
                            Player[] rowSequence = new Player[6];

                            for (int b = row + 1, i = 0; b >= 0 && b > row - 5; b--, i++) {
                                rowSequence[i] = board[b][col];
                            }

                            if (isStraightFour(rowSequence, player)) {//If it is a straight 4
                                evaluation += STRAIGHT_FOUR_POINTS;
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left
                                if (board[row + 1][col] != enemy) {
                                    evaluation += FOURS_POINTS;
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
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                            } else if (lastEnemyEncounteredRow > -1) {//we encountered an enemy before seeing our player
                                if ((row + 1 < boardLength && col - 1 >= 0) && (encounteredEnemy == row + 4 && encounteredEnemyY == col + 4)) {//we have enough room to make a 4, check upwards to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row + 1][col - 1] != enemy) {
                                        evaluation += THREES_POINTS;
                                    }
                                } else if (row - 2 >= 0 && col - 2 >= 0 && (encounteredEnemy == row + 3 && encounteredEnemyY == col + 3)) {//we are stuck on 3, check upwards 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                }
                            }
                        } else if (count == 4 && (col - 1 < 0 && row - 1 < 0) && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (row + 5 >= boardLength || row - 1 < 0) && (col + 5 >= boardLength || col - 1 < 0)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                        } else { //check for the straight four
                            Player[] rowSequence = new Player[6];

                            for (int b = row - 1, c = col - 1, i = 0; b < boardLength && b < row + 5 && b >= 0 && c >= 0; b++, c++, i++) {
                                rowSequence[i] = board[b][c];
                            }

                            if (isStraightFour(rowSequence, player)) {//If it is a straight 4
                                evaluation += STRAIGHT_FOUR_POINTS;
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left
                                if (row - 1 >= 0 && col - 1 >= 0 && board[row - 1][col - 1] != enemy) {
                                    evaluation += FOURS_POINTS;
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
                        } else if (count == 3) {
                            if (encounteredEnemy == -1) {
                                evaluation += THREES_POINTS;
                            } else if (lastEnemyEncounteredRow > -1) {//we encountered an enemy before seeing our player
                                if ((row - 1 >= 0 && col - 1 >= 0) && (encounteredEnemy == row - 4 && encounteredEnemyY == col + 4)) {//we have enough room to make a 4, check upwards to see if we can make a 5 (-O-X-XXO--)
                                    if (board[row - 1][col - 1] != enemy) {
                                        evaluation += THREES_POINTS;
                                    }
                                } else if (row + 2 < boardLength && col - 2 >= 0 && (encounteredEnemy == row - 3 && encounteredEnemyY == col + 3)) {//we are stuck on 3, check upwards 2 to see if we can make it a 5
                                    evaluation += THREES_POINTS;
                                }
                            }
                        } else if (count == 4 && (col - 1 < 0 && row + 1 >= boardLength) && encounteredEnemy == -1) {//havent encountered an enemy before seeing our player
                            evaluation += FOURS_POINTS;
                        } else if (encounteredEnemy > -1 && (row - 5 < 0 || row + 1 >= boardLength) && (col + 5 >= boardLength || col - 1 < 0)) {
                            //enemy is blocking us at the edge of the board (OXXXX)
                        } else { //check for the straight four
                            Player[] rowSequence = new Player[6];

                            for (int b = row + 1, c = col - 1, i = 0; b < boardLength && b > row - 5 && c >= 0; b--, c++, i++) {
                                rowSequence[i] = board[b][c];
                            }

                            if (isStraightFour(rowSequence, player)) {//If it is a straight 4
                                evaluation += STRAIGHT_FOUR_POINTS;
                            } else if (encounteredEnemy == -1) {//If it is possible to have a straight 4, and we have not encountered an enemy while searching
                                evaluation += FOURS_POINTS;
                            } else { //If it is possible to have a straight 4, but we have encountered an enemy while searching, check if there is room on left

                                if (board[row + 1][col - 1] != enemy) {
                                    evaluation += FOURS_POINTS;
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
       // return ThreatUtils.matchPattern(sequence, player, true) != -1;
        Player[] straightFour = new Player[]{null, player, player, player, player, null};
        // if (DEBUG) System.out.println("IsStraightFour? " + in.replaceAll(" ", "-"));
        //boolean isFour = true;
        for (int i = 0; i < 6; i++) {
            if (sequence[i] != straightFour[i])
                return false;
        }
        return true;//sequence.equals(straightFour);
    }

    public BoardCell concat(Player player, boolean block) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 10; row++) {
            int i = 0;
            //start Martin i Bartek
            /*if (block) {
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
            }*/
            //end Martin i Bartek
            i = 0;
            while (i < 6) {
                Player[] sequence = new Player[5];
                for (int j = i; j < i + 5; j++)
                    sequence[j - i] = board[row][j];
                int where = ThreatUtils.matchPattern(sequence, player, block);
                if (where != -1)
                    return new BoardCell(row, i + where, null);
                i++;
            }
        }


        // Checking for Columns for X or O victory.
        for (int col = 0; col < 10; col++) {
            int i = 0;
            //start Martin i Bartek
            /*while (i <= 7) {
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
            }*/
            //end Martin i Bartek
            i = 0;
            while (i < 6) {
                Player[] sequence = new Player[5];
                for (int j = i; j < i + 5; j++)
                    sequence[j - i] = board[j][col];
                int where = patterns.matchPattern(sequence, player, block);
                if (where != -1) {
                    return new BoardCell(i + where, col, null);
                }
                i++;
            }
        }
        // Checking for diagonals going from top left corner to bottom right corner.
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                /*if (board[row][col] == board[row + 1][col + 1] && board[row + 1][col + 1] == board[row + 2][col + 2] && board[row][col] != null) {
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
                }*/
                //end Martin i Bartek
                Player[] sequence = new Player[5];
                for (int j = 0; j < 5; j++)
                    sequence[j] = board[row + j][col + j];
                int where = patterns.matchPattern(sequence, player, block);
                if (where != -1) {
                    return new BoardCell(row + where, col + where, null);
                }

            }

        }
        // Checking for diagonals going from top right corner to bottom left corner.
        for (int row = 0; row < 6; row++) {
            for (int col = 9; col >= 5; col--) {
                /*if (board[row][col] == board[row + 1][col - 1] && board[row + 1][col - 1] == board[row + 2][col - 2] && board[row][col] != null) {
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
                }*/
                //end Martin i Bartek
                Player[] sequence = new Player[5];
                for (int j = 0; j < 5; j++)
                    sequence[j] = board[row + j][col - j];
                int where = patterns.matchPattern(sequence, player, block);
                if (where != -1) {
                    return new BoardCell(row + where, col - where, null);
                }
            }
        }

        return null;
    }


}
