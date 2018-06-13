package com.capgemini.algorithmbattles.ballsapp.solution;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Board;
import com.capgemini.algorithmbattles.ballsapp.logic.model.BoardCell;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class GamePlayer {

    private Player player;
    private Board board = new Board();
    int numOfMoves;
    int maxDepth;

    private final ThreatUtils reducer;
    private final Evaluator evaluator;
    private final Cache<Long, MoveEntry> moveTable;

    private int totalNodeCount;
    private int nonLeafCount;
    private int branchesExploredSum;

    private State state;

    private class MoveEntry {
        BoardCell move;
        int depth;

        public MoveEntry(BoardCell move, int depth) {
            this.move = move;
            this.depth = depth;
        }
    }

    private class ScoredMove implements Comparable<ScoredMove> {
        public BoardCell move;
        public int score;
        public ScoredMove(BoardCell move, int score) {
            this.move = move;
            this.score = score;
        }

        @Override
        public int compareTo(ScoredMove move) {
            return move.score - this.score;
        }
    }


    public GamePlayer(Player player) {
        this.player = player;
        numOfMoves = 0;
        board.setPlayer(player);
        this.reducer = new ThreatUtils();
        this.evaluator = Evaluator.getInstance();
        this.moveTable = new Cache<>(1000000);
    }

    /**
     * Generate a list of sorted and pruned moves for this state. Moves are
     * pruned when they are too far away from existing stones, and also when
     * threats are found which require an immediate response.
     * @param state State to get moves for
     * @return A list of moves, sorted and pruned
     */
    private List<BoardCell> getSortedMoves(State state) {
        // Board is empty, return a move in the middle of the board
        if(state.getMoves() == 0) {
            List<BoardCell> moves = new ArrayList<>();
            moves.add(new BoardCell(state.board.length / 2, state.board.length / 2, Player.PLAYER_1));
            return moves;
        }

        int playerIndex = state.currentIndex;
        int opponentIndex = state.currentIndex == 2 ? 1 : 2;

        HashSet<BoardCell> fours = new HashSet<>();
        HashSet<BoardCell> refutations = new HashSet<>();

        HashSet<BoardCell> opponentFours = new HashSet<>();
        HashSet<BoardCell> opponentThrees = new HashSet<>();

        // Check for threats first and respond to them if they exist
        for(int i = 0; i < state.board.length; i++) {
            for(int j = 0; j < state.board.length; j++) {
                if(state.board[i][j].index == opponentIndex) {
                    opponentFours.addAll(reducer.getFours(state,
                            state.board[i][j], opponentIndex));
                    opponentThrees.addAll(reducer.getThrees(state,
                            state.board[i][j], opponentIndex));
                }
                else if(state.board[i][j].index == playerIndex) {
                    fours.addAll(reducer.getFours(state, state.board[i][j],
                            playerIndex));
                    refutations.addAll(reducer.getRefutations(state, state
                            .board[i][j], playerIndex));
                }
            }
        }

        // We have a four on the board, play it
        if(!fours.isEmpty()) {
            return new ArrayList<>(fours);
        }
        // Opponent has a four, defend against it
        if(!opponentFours.isEmpty()) {
            return new ArrayList<>(opponentFours);
        }
        // Opponent has a three, defend against it and add refutation moves
        if(!opponentThrees.isEmpty()) {
            opponentThrees.addAll(refutations);
            return new ArrayList<>(opponentThrees);
        }

        List<ScoredMove> scoredMoves = new ArrayList<>();

        MoveEntry entry = moveTable.get(state.getZobristHash());
        // Grab closest moves
        List<BoardCell> moves = new ArrayList<>();
        for(int i = 0; i < state.board.length; i++) {
            for(int j = 0; j < state.board.length; j++) {
                // Ignore hash move
                if(entry != null &&
                        (i == entry.move.getX() && j == entry.move.getY())) {
                    continue;
                }
                if(state.board[i][j].index == 0) {
                    if(state.hasAdjacent(i, j, 2)) {
                        int score = evaluator.evaluateField(state, i, j,
                                state.currentIndex);
                        scoredMoves.add(new ScoredMove(new BoardCell(i, j, Player.PLAYER_1), score));
                    }
                }
            }
        }

        // Sort based on move score
        Collections.sort(scoredMoves);
        for(ScoredMove move : scoredMoves) {
            moves.add(move.move);
        }
        return moves;
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


        if (0 < numOfMoves && numOfMoves < 25)
            maxDepth = 1;
        if (25 <= numOfMoves && numOfMoves < 34)
            maxDepth = 4;
        if (34 <= numOfMoves && numOfMoves < 38)
            maxDepth = 5;
        if (38 <= numOfMoves && numOfMoves < 40)
            maxDepth = 6;
        if (40 <= numOfMoves && numOfMoves < 42)
            maxDepth = 7;
        if (42 <= numOfMoves && numOfMoves < 44)
            maxDepth = 8;

        double bestVal = Double.MIN_VALUE;
        BoardCell bestCell = new BoardCell(-1, -1, player);

        Player[][] b = board.getBoard();

        /*BoardCell blockade = board.evaluate2(player.getOther());
        if (blockade!= null)
            return blockade;*/


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

        double score = 0;

        //if (b.getNumFilledCells() > 5)
            score = b.evaluate(player);
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
            if (isMax)
                return b.getGameState(player);
            else
                return -b.getGameState(player.getOther());
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
