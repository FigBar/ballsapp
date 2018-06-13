package com.capgemini.algorithmbattles.ballsapp.solution;

import com.capgemini.algorithmbattles.ballsapp.logic.model.BoardCell;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Player;

import java.util.ArrayList;
import java.util.List;

public class ThreatUtils {

    List<ThreatPattern> REFUTATIONS;
    List<ThreatPattern> THREES;
    List<ThreatPattern> FOURS;
    Player FIRST = Player.PLAYER_1;
    Player SECOND = Player.PLAYER_2;

    public ThreatUtils() {
        this.THREES = new ArrayList<>();
        this.FOURS = new ArrayList<>();
        this.REFUTATIONS = new ArrayList<>();

        THREES.add(new ThreatPattern(new Player[] {null, FIRST, FIRST, FIRST, null, null}, new int[]
                {0, 4, 5}));
        THREES.add(new ThreatPattern(new Player[] {null, null, FIRST, FIRST, FIRST, null}, new int[]
                {0, 1, 5}));
        THREES.add(new ThreatPattern(new Player[] {null, FIRST, null, FIRST, FIRST, null}, new int[]
                {0, 2, 5}));
        THREES.add(new ThreatPattern(new Player[] {null, FIRST, FIRST, null, FIRST, null}, new int[]
                {0, 3, 5}));

        FOURS.add(new ThreatPattern(new Player[] {FIRST, FIRST, FIRST, FIRST, null}, new int[] {4} ));
        FOURS.add(new ThreatPattern(new Player[] {FIRST, FIRST, FIRST, null, FIRST}, new int[] {3} ));
        FOURS.add(new ThreatPattern(new Player[] {FIRST, FIRST, null, FIRST, FIRST}, new int[] {2} ));
        FOURS.add(new ThreatPattern(new Player[] {FIRST, null, FIRST, FIRST, FIRST}, new int[] {1} ));
        FOURS.add(new ThreatPattern(new Player[] {null, FIRST, FIRST, FIRST, FIRST}, new int[] {0} ));

        REFUTATIONS.add(new ThreatPattern(new Player[] {FIRST, FIRST, FIRST, null, null}, new
                int[] {3, 4}));
        REFUTATIONS.add(new ThreatPattern(new Player[] {FIRST, FIRST, null, null, FIRST}, new
                int[] {2, 3} ));
        REFUTATIONS.add(new ThreatPattern(new Player[] {FIRST, null, null, FIRST, FIRST}, new
                int[] {1, 2} ));
        REFUTATIONS.add(new ThreatPattern(new Player[] {null, null, FIRST, FIRST, FIRST}, new
                int[] {0, 1} ));
    }

    /**
     * Check a field for a broken three or a straight three pattern on the
     * board (0XXX0 and 0X0XX0) belonging to a player.
     * @param playerIndex Player index
     * @return List of moves corresponding to the offensive squares of the
     * threat
     */
    public List<BoardCell> getThrees(State state, Field field, int playerIndex) {
        return getThreatMoves(THREES, state, field, playerIndex);
    }

    /**
     * Check a field for a broken three or a straight three pattern on the
     * board (0XXX0 and 0X0XX0) belonging to a player.
     * @param playerIndex Player index
     * @return List of moves corresponding to the offensive/defensive squares of
     * the threat
     */
    public List<BoardCell> getFours(State state, Field field, int playerIndex) {
        return getThreatMoves(FOURS, state, field, playerIndex);
    }
    /**
     * Check a field for a pattern which can turn into a four, e.g. 00XXX
     * @param playerIndex Player index
     * @return List of moves corresponding to the offensive/defensive squares of
     * the refutation
     */
    public List<BoardCell> getRefutations(State state, Field field, int
            playerIndex) {
        return getThreatMoves(REFUTATIONS, state, field, playerIndex);
    }

    /**
     * Search for threats around a field in a game state, mapping each threat
     * to offensive/defensive moves if found.
     * @param patternList List of ThreatPattern objects to search for
     * @param state State to search
     * @param field Field to search around
     * @param playerIndex Player index to search for
     * @return
     */
    private List<BoardCell> getThreatMoves(List<ThreatPattern> patternList, State
            state, Field field, int playerIndex) {
        List<BoardCell> threatMoves = new ArrayList<>();
        // Loop around the field in every direction
        // (diagonal/horizontal/vertical)
        for(int direction = 0; direction < 4; direction++) {
            Field[] directionArray = state.directions[field.row][field.col]
                    [direction];
            for(ThreatPattern pattern : patternList) {
                // Try to find the pattern
                int patternIndex = matchPattern(directionArray, pattern.getPattern(playerIndex));
                if(patternIndex != -1) {
                    // Found pattern, get the squares in the pattern and map
                    // them to moves on the board
                    for(int patternSquareIndex : pattern.getPatternSquares()) {
                        Field patternSquareField = directionArray[patternIndex +
                                patternSquareIndex];
                        threatMoves.add(new BoardCell(patternSquareField.row,
                                patternSquareField.col, FIRST));
                    }
                }
            }
        }
        return threatMoves;
    }

    /**
     * Search for a pattern in a field array.
     * @param direction Field array
     * @param pattern Pattern to match e.g. [2 0 2 2]
     * @return The starting index if found, or -1 if not found
     */
    private int matchPattern(Field[] direction, Player[] pattern) {
        for(int i = 0; i < direction.length; i++) {
            // Check if the pattern lies within the bounds of the direction
            if(i + (pattern.length - 1) < direction.length) {
                int count = 0;
                for(int j = 0; j < pattern.length; j++) {
                    if(direction[i + j].index == pattern[j].getIndex()) {
                        count++;
                    } else {
                        break;
                    }
                }
                // Every element was the same, return the start index
                if(count == pattern.length) {
                    return i;
                }
            } else {
                break;
            }
        }
        return -1;
    }

}
