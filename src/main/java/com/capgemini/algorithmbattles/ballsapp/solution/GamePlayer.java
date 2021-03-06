package com.capgemini.algorithmbattles.ballsapp.solution;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Board;
import com.capgemini.algorithmbattles.ballsapp.logic.model.BoardCell;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Player;

public class GamePlayer {

    private Player player;
    private Board board = new Board();
    int numOfMoves;

    public GamePlayer(Player player) {
        this.player = player;
        numOfMoves= 0;
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

        BoardCell cell = board.getFirstEmptyCell();
        if(numOfMoves<40){
            numOfMoves++;
            return board.getFirstEmptyCell();
        }

        int bestVal = -1000;
        BoardCell bestCell = new BoardCell(-1, -1, player);

        Player[][] b = board.getBoard();

        // Traverse all cells, evalutae minimax function for
        // all empty cells. And return the cell with optimal
        // value.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Check if cell is empty
                if (b[i][j] == null) {
                    // Make the move
                    board.placeMove(new BoardCell(i,j, player));

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, true, -1000000, 1000000);

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

    private int minimax(Board b, int depth, boolean isMax, int alpha, int beta) {
        int score = b.evaluate(player);

        //Player[][] matrix = b.getBoard();

        // If Maximizer has won the game return his/her
        // evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game return his/her
        // evaluated score
        if (score == -10)
            return score ;

        // If there are no more moves and no winner then
        // it is a tie
        if (b.getFirstEmptyCell() == null)
            return 0;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    // Check if cell is empty
                    if (b.getPlayerAt(i, j) == null) {
                        // Make the move
                        b.placeMove(new BoardCell(i, j, player));

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best,
                                minimax(b, depth + 1, !isMax, alpha, beta));

                        // Undo the move
                        b.remove(i, j);

                        alpha = Math.max(alpha, best);
                        if (beta <= alpha)
                            return alpha;
                    }
                }
            }
            return alpha;
        }

        // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    // Check if cell is empty
                    if (b.getPlayerAt(i, j) == null) {
                        // Make the move
                        b.placeMove(new BoardCell(i, j, player.getOther()));

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best,
                                minimax(b, depth + 1, !isMax, alpha, beta));

                        //return beta

                        // Undo the move
                        b.remove(i, j);

                        beta = Math.min( beta, best);
                        if (beta <= alpha)
                            return beta;
                    }
                }
            }
            return beta;
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
