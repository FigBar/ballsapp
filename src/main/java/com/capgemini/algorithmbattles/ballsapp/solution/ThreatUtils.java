package com.capgemini.algorithmbattles.ballsapp.solution;

import com.capgemini.algorithmbattles.ballsapp.logic.model.BoardCell;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Player;

import java.util.ArrayList;
import java.util.List;

public class ThreatUtils {

    public static List<Player[]> REFUTATIONS;
    public static List<Player[]> THREES;
    public static List<Player[]> FIRSTFOURS;
    public static List<Player[]> FIRSTFOURS2;
    public static List<Player[]> SECONDFOURS;
    public static List<Player[]> SECONDFOURS2;
    public static Player FIRST = Player.PLAYER_1;
    public static Player SECOND = Player.PLAYER_2;

    public ThreatUtils() {
        this.THREES = new ArrayList<>();
        this.FIRSTFOURS = new ArrayList<>();
        this.SECONDFOURS = new ArrayList<>();
        this.REFUTATIONS = new ArrayList<>();
        this.FIRSTFOURS2 = new ArrayList<>();
        this.SECONDFOURS2 = new ArrayList<>();

        /*THREES.add(new Player[] {null, FIRST, FIRST, FIRST, null, null});
        THREES.add(new Player[] {null, null, FIRST, FIRST, FIRST, null});
        THREES.add(new Player[] {null, FIRST, null, FIRST, FIRST, null});
        THREES.add(new Player[] {null, FIRST, FIRST, null, FIRST, null});*/

        //FOURS.add(new Player[] {null, FIRST, FIRST, FIRST, FIRST, null});
        FIRSTFOURS.add(new Player[]{FIRST, FIRST, FIRST, null, FIRST});
        FIRSTFOURS.add(new Player[]{FIRST, FIRST, null, FIRST, FIRST});
        FIRSTFOURS.add(new Player[]{FIRST, null, FIRST, FIRST, FIRST});

        FIRSTFOURS.add(new Player[]{FIRST, FIRST, FIRST, FIRST, null});
        FIRSTFOURS.add(new Player[]{null, FIRST, FIRST, FIRST, FIRST});

        FIRSTFOURS2.addAll(FIRSTFOURS);

        FIRSTFOURS.add(new Player[]{null, FIRST, FIRST, FIRST, null});

        /*FIRSTFOURS2.add(new Player[]{FIRST, FIRST, FIRST, FIRST, null});
        FIRSTFOURS2.add(new Player[]{null, FIRST, FIRST, FIRST, FIRST});*/

        SECONDFOURS.add(new Player[]{SECOND, SECOND, SECOND, null, SECOND});
        SECONDFOURS.add(new Player[]{SECOND, SECOND, null, SECOND, SECOND});
        SECONDFOURS.add(new Player[]{SECOND, null, SECOND, SECOND, SECOND});
        SECONDFOURS.add(new Player[]{SECOND, SECOND, SECOND, SECOND, null});
        SECONDFOURS.add(new Player[]{null, SECOND, SECOND, SECOND, SECOND});

        SECONDFOURS2.addAll(SECONDFOURS);

        SECONDFOURS.add(new Player[]{null, SECOND, SECOND, SECOND, null});

        /*SECONDFOURS2.add(new Player[]{SECOND, SECOND, SECOND, SECOND, null});
        SECONDFOURS2.add(new Player[]{null, SECOND, SECOND, SECOND, SECOND});*/

        //FOURS.add(new Player[] {null, FIRST, FIRST, FIRST, FIRST, null});

        /*REFUTATIONS.add(new ThreatPattern(new Player[] {FIRST, FIRST, FIRST, null, null}, new
                int[] {3, 4}));
        REFUTATIONS.add(new ThreatPattern(new Player[] {FIRST, FIRST, null, null, FIRST}, new
                int[] {2, 3} ));
        REFUTATIONS.add(new ThreatPattern(new Player[] {FIRST, null, null, FIRST, FIRST}, new
                int[] {1, 2} ));
        REFUTATIONS.add(new ThreatPattern(new Player[] {null, null, FIRST, FIRST, FIRST}, new
                int[] {0, 1} ));*/
    }

    /* *//**
     * Check a field for a broken three or a straight three pattern on the
     * board (0XXX0 and 0X0XX0) belonging to a player.
     *
     * @param playerIndex Player index
     * @return List of moves corresponding to the offensive squares of the
     * threat
     *//*
    public List<BoardCell> getThrees(State state, Field field, int playerIndex) {
        return getThreatMoves(THREES, state, field, playerIndex);
    }

    *//**
     * Check a field for a broken three or a straight three pattern on the
     * board (0XXX0 and 0X0XX0) belonging to a player.
     *
     * @param playerIndex Player index
     * @return List of moves corresponding to the offensive/defensive squares of
     * the threat
     *//*
    public List<BoardCell> getFours(State state, Field field, int playerIndex) {
        return getThreatMoves(FOURS, state, field, playerIndex);
    }

    */

    /**
     * Check a field for a pattern which can turn into a four, e.g. 00XXX
     *
     * @param playerIndex Player index
     * @return List of moves corresponding to the offensive/defensive squares of
     * the refutation
     *//*
    public List<BoardCell> getRefutations(State state, Field field, int
            playerIndex) {
        return getThreatMoves(REFUTATIONS, state, field, playerIndex);
    }


    private List<BoardCell> getThreatMoves(List<ThreatPattern> patternList, State
            state, Field field, int playerIndex) {
        List<BoardCell> threatMoves = new ArrayList<>();
        // Loop around the field in every direction
        // (diagonal/horizontal/vertical)
        for (int direction = 0; direction < 4; direction++) {
            Field[] directionArray = state.directions[field.row][field.col]
                    [direction];
            for (ThreatPattern pattern : patternList) {
                // Try to find the pattern
                int patternIndex = matchPattern(directionArray, pattern.getPattern(playerIndex));
                if (patternIndex != -1) {
                    // Found pattern, get the squares in the pattern and map
                    // them to moves on the board
                    for (int patternSquareIndex : pattern.getPatternSquares()) {
                        Field patternSquareField = directionArray[patternIndex +
                                patternSquareIndex];
                        threatMoves.add(new BoardCell(patternSquareField.row,
                                patternSquareField.col, FIRST));
                    }
                }
            }
        }
        return threatMoves;
    }*/
    public static int matchPattern(Player[] pattern, Player player, boolean block) {
        int sum = 0;
        if (block) {
            if (player == FIRST) {
                for (Player[] a : FIRSTFOURS) {
                    if (sum == 5) break;
                    sum = 0;
                    for (int i = 0; i < a.length; i++) {
                        if (a[i] == pattern[i]) sum++;
                        if (sum>=5) break;
                    }
                }
                if (sum == 5) {
                    for (int i = 0; i < 5; i++)
                        if (pattern[i] == null)
                            return i;
                }
            }

            if (player == SECOND) {
                for (Player[] a : SECONDFOURS) {
                    if (sum == 5) break;
                    sum = 0;
                    for (int i = 0; i < a.length; i++) {
                        if (a[i] == pattern[i]) sum++;
                        if (sum>=5) break;
                    }
                }
                if (sum == 5) {
                    for (int i = 0; i < 5; i++) {
                        if (pattern[i] == null)
                            return i;
                    }
                }
            }

            return -1;
        } else {
            if (player == FIRST) {
                for (Player[] a : FIRSTFOURS2) {
                    if (sum == 5) break;
                    sum = 0;
                    for (int i = 0; i < a.length; i++) {
                        if (a[i] == pattern[i]) sum++;
                        if (sum>=5) break;
                    }
                }
                if (sum == 5) {
                    for (int i = 0; i < 5; i++) {
                        if (pattern[i] == null)
                            return i;
                    }
                }
            }
            if (player == SECOND) {
                for (Player[] a : SECONDFOURS2) {
                    if (sum == 5) break;
                    sum = 0;
                    for (int i = 0; i < a.length; i++) {
                        if (a[i] == pattern[i]) sum++;
                        if (sum>=5) break;
                    }
                }
                if (sum == 5) {
                    for (int i = 0; i < 5; i++) {
                        if (pattern[i] == null)
                            return i;
                    }
                }
            }

            return -1;
        }
    }

}
