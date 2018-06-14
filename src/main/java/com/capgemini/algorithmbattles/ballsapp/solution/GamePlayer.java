package com.capgemini.algorithmbattles.ballsapp.solution;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Board;
import com.capgemini.algorithmbattles.ballsapp.logic.model.BoardCell;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Player;

public class GamePlayer {

    private Player player;
    private Board board = new Board();
    int numOfMoves;
    int maxDepth;

    public GamePlayer(Player player) {
        this.player = player;
        numOfMoves = 0;
        // maxDepth = 100;
    }

    /**
     * The application should calculate the next move after this method call.
     *
     * @return the next {@link BoardCell move} for current player.
     */
    public BoardCell nextMove() {
        BoardCell cell = getCellForNextMove();
        cell.setPlayer(player);
        board.placeMove(cell);
        return cell;
    }

    private BoardCell getCellForNextMove() {
        // TODO: Please implement it.
        numOfMoves++;
        maxDepth = 1000;


        if (0 < numOfMoves && numOfMoves < 29)
            maxDepth = 1;
        if (29 <= numOfMoves && numOfMoves < 34)
            maxDepth = 2;
        if (34 <= numOfMoves && numOfMoves < 38)
            maxDepth = 5;
        if (38 <= numOfMoves && numOfMoves < 40)
            maxDepth = 6;
        if (40 <= numOfMoves && numOfMoves < 42)
            maxDepth = 7;
        if (42 <= numOfMoves && numOfMoves < 44)
            maxDepth = 8;

        double bestVal = Double.NEGATIVE_INFINITY;
        BoardCell bestCell = new BoardCell(-1, -1, player);

        Player[][] b = board.getBoard();

        BoardCell killer = board.concat(player, false);
        if(killer!= null){
            return killer;
        }

        BoardCell blockade = board.concat(player.getOther(), true);
        if (blockade!= null) {
            return blockade;
        }


        // Traverse all cells, evaluate minimax function for
        // all empty cells. And return the cell with optimal
        // value.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Check if cell is empty
                if (b[i][j] == null) {
                    // Make the move
                    BoardCell current = new BoardCell(i, j, player);
                    board.placeMove(current);

                    // compute evaluation function for this
                    // move.
                    double moveVal = minimax(board, 0, false, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

                    // Undo the move
                    board.remove(i, j);

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestCell.setX(i);
                        bestCell.setY(j);
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestCell;
    }

    private double minimax(Board b, int depth, boolean isMax, double alpha, double beta) {

        /*double score = 0;

        if (b.getNumFilledCells() > 5)*/
        double score = b.evaluate(player);
        //double score2 = -b.scoreGameState(player.getOther());


        // If Maximizer has won the game return his
        // evaluated score
        if (score == Board.FIVE_IN_A_ROW)
            return score - depth;

        // If Minimizer has won the game return his/her
        // evaluated score
        if (score == -Board.FIVE_IN_A_ROW)
            return score + depth;

        // If there are no more moves and no winner then
        // it is a tie
        if (b.isFull())
            return 0;

        //maximum depth has been reached
        if (depth == this.maxDepth) {
            return b.gameStateValue(player);
            /*if (isMax)
                return b.gameStateValue(player);
            else
                return b.gameStateValue(player);*/
        }


        // If this maximizer's move
        if (isMax) {
            double best = Double.NEGATIVE_INFINITY;

            // Traverse all cells
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    // Check if cell is empty
                    if (b.getPlayerAt(i, j) == null) {
                        // Make the move
                        BoardCell current = new BoardCell(i, j, player);
                        b.placeMove(current);

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best,
                                minimax(b, depth + 1, !isMax, alpha, beta));

                        // Undo the move
                        b.remove(i, j);

                        if (best >= beta)
                            return best;

                        alpha = Math.max(alpha, best);
                        /*if (beta <= alpha)
                            return alpha;//?*/
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            double best = Double.POSITIVE_INFINITY;

            // Traverse all cells
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    // Check if cell is empty
                    if (b.getPlayerAt(i, j) == null) {
                        // Make the move
                        BoardCell current = new BoardCell(i, j, player);
                        b.placeMove(current);

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best,
                                minimax(b, depth + 1, !isMax, alpha, beta));

                        //return beta

                        // Undo the move
                        b.remove(i, j);

                        if (best <= alpha)
                            return best;

                        beta = Math.min(beta, best);
                        /*if (beta <= alpha)
                            return beta;//?*/
                    }
                }
            }
            return best;
        }
    }


    /**
     * The opponent made the move passed in param.
     *
     * @param move the {@link BoardCell} made by the opponent.
     */
    public void moveMadeByOpponent(BoardCell move) {
        this.board.placeMove(move);
    }

    /**
     * @return true if the game is finished
     */
    public boolean isGameFinished() {
        return this.board.isGameFinished();
    }

    /**
     * Draw the board on the console.
     */
    public void printBoard() {
        this.board.printBoard();
    }
}
