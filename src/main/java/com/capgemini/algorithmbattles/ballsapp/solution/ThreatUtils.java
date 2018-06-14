package com.capgemini.algorithmbattles.ballsapp.solution;

import com.capgemini.algorithmbattles.ballsapp.logic.model.BoardCell;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Player;

import java.util.ArrayList;
import java.util.List;

public class ThreatUtils {

    public static List<Player[]> REFUTATIONS1;
    public static List<Player[]> REFUTATIONS2;
    public static List<Player[]> THREES;
    public static List<Player[]> THREES2;
    public static List<Player[]> FIRSTFOURS;
    public static List<Player[]> FIRSTFOURS2;
    public static List<Player[]> SECONDFOURS;
    public static List<Player[]> SECONDFOURS2;
    public static List<Player[]> MIXEDTHREES;
    public static List<Player[]> MIXEDTHREES2;
    public static List<Player[]> SPECIFIC_THREES;
    public static List<Player[]> SPECIFIC_THREES2;

    public static Player FIRST = Player.PLAYER_1;
    public static Player SECOND = Player.PLAYER_2;

    public static final double FIVE_IN_A_ROW = Double.POSITIVE_INFINITY - 1;
    public static final double STRAIGHT_FOUR_POINTS = 1000;
    public static final double FOURS_POINTS = 500;
    public static final double THREES_POINTS = 160;
    public static final double MIXED_THREES_POINTS = 100;

    public static final double TWOS_POINTS = 5;
    public static final double ONES_POINTS = 1;

    public ThreatUtils() {
        this.THREES = new ArrayList<>();
        this.THREES2 = new ArrayList<>();
        this.FIRSTFOURS = new ArrayList<>();
        this.SECONDFOURS = new ArrayList<>();
        this.FIRSTFOURS2 = new ArrayList<>();
        this.SECONDFOURS2 = new ArrayList<>();
        this.MIXEDTHREES = new ArrayList<>();
        this.MIXEDTHREES2 = new ArrayList<>();
        this.SPECIFIC_THREES = new ArrayList<>();
        this.SPECIFIC_THREES2 = new ArrayList<>();

        this.REFUTATIONS1 = new ArrayList<>();
        this.REFUTATIONS2 = new ArrayList<>();


        THREES.add(new Player[]{FIRST, null, FIRST, FIRST, null, null});
        THREES.add(new Player[]{FIRST, FIRST, null, FIRST, null, null});
        THREES.add(new Player[]{null, FIRST, null, FIRST, FIRST, null});
        THREES.add(new Player[]{null, FIRST, FIRST, null, FIRST, null});
        THREES.add(new Player[]{null, null, FIRST, FIRST, null, FIRST});
        THREES.add(new Player[]{null, null, FIRST, null, FIRST, FIRST});

        SPECIFIC_THREES.add(new Player[]{null, FIRST, FIRST, FIRST, null, null});
        SPECIFIC_THREES.add(new Player[]{null, null, FIRST, FIRST, FIRST, null});

        THREES2.add(new Player[]{SECOND, null, SECOND, SECOND, null, null});
        THREES2.add(new Player[]{SECOND, SECOND, null, SECOND, null, null});
        THREES2.add(new Player[]{null, SECOND, null, SECOND, SECOND, null});
        THREES2.add(new Player[]{null, SECOND, SECOND, null, SECOND, null});
        THREES2.add(new Player[]{null, null, SECOND, SECOND, null, SECOND});
        THREES2.add(new Player[]{null, null, SECOND, null, SECOND, SECOND});

        SPECIFIC_THREES2.add(new Player[]{null, SECOND, SECOND, SECOND, null, null});
        SPECIFIC_THREES2.add(new Player[]{null, null, SECOND, SECOND, SECOND, null});

        /*MIXEDTHREES.addAll(THREES);
        MIXEDTHREES2.addAll(THREES2);*/

        MIXEDTHREES.add(new Player[]{FIRST, null, FIRST, FIRST, null, SECOND});
        MIXEDTHREES.add(new Player[]{FIRST, FIRST, FIRST, null, null, SECOND});
        MIXEDTHREES.add(new Player[]{FIRST, null, null, FIRST, FIRST, SECOND});
        MIXEDTHREES.add(new Player[]{null, null, FIRST, FIRST, FIRST, SECOND});
        MIXEDTHREES.add(new Player[]{null, FIRST, null, FIRST, FIRST, SECOND});
        MIXEDTHREES.add(new Player[]{null, FIRST, FIRST, null, FIRST, SECOND});
        MIXEDTHREES.add(new Player[]{null, FIRST, FIRST, FIRST, null, SECOND});

        MIXEDTHREES.add(new Player[]{null, FIRST, FIRST, FIRST, null, null});
        MIXEDTHREES.add(new Player[]{null, null, FIRST, FIRST, FIRST, null});
        MIXEDTHREES.add(new Player[]{null, null, null, FIRST, FIRST, FIRST});
        MIXEDTHREES.add(new Player[]{FIRST, FIRST, FIRST, null, null, null});


        MIXEDTHREES.add(new Player[]{SECOND, null, FIRST, FIRST, FIRST, null});
        MIXEDTHREES.add(new Player[]{SECOND, null, FIRST, FIRST, null, FIRST});
        MIXEDTHREES.add(new Player[]{SECOND, null, FIRST, null, FIRST, FIRST});
        MIXEDTHREES.add(new Player[]{SECOND, FIRST, FIRST, FIRST, null, null});
        MIXEDTHREES.add(new Player[]{SECOND, null, null, FIRST, FIRST, FIRST});
        MIXEDTHREES.add(new Player[]{SECOND, FIRST, null, FIRST, FIRST, null});

        MIXEDTHREES2.add(new Player[]{SECOND, null, SECOND, SECOND, null, FIRST});
        MIXEDTHREES2.add(new Player[]{SECOND, SECOND, SECOND, null, null, FIRST});
        MIXEDTHREES2.add(new Player[]{SECOND, null, null, SECOND, SECOND, FIRST});
        MIXEDTHREES2.add(new Player[]{null, null, SECOND, SECOND, SECOND, FIRST});
        MIXEDTHREES2.add(new Player[]{null, SECOND, null, SECOND, SECOND, FIRST});
        MIXEDTHREES2.add(new Player[]{null, SECOND, SECOND, null, SECOND, FIRST});
        MIXEDTHREES2.add(new Player[]{null, SECOND, SECOND, SECOND, null, FIRST});


        MIXEDTHREES2.add(new Player[]{FIRST, null, SECOND, SECOND, SECOND, null});
        MIXEDTHREES2.add(new Player[]{FIRST, null, SECOND, SECOND, null, SECOND});
        MIXEDTHREES2.add(new Player[]{FIRST, null, SECOND, null, SECOND, SECOND});
        MIXEDTHREES2.add(new Player[]{FIRST, SECOND, SECOND, SECOND, null, null});
        MIXEDTHREES2.add(new Player[]{FIRST, null, null, SECOND, SECOND, SECOND});
        MIXEDTHREES2.add(new Player[]{FIRST, SECOND, null, SECOND, SECOND, null});
        MIXEDTHREES2.add(new Player[]{FIRST, null, SECOND, SECOND, SECOND, null});

        MIXEDTHREES2.add(new Player[]{null, SECOND, SECOND, SECOND, null, null});
        MIXEDTHREES2.add(new Player[]{null, null, SECOND, SECOND, SECOND, null});
        MIXEDTHREES2.add(new Player[]{null, null, null, SECOND, SECOND, SECOND});
        MIXEDTHREES2.add(new Player[]{SECOND, SECOND, SECOND, null, null, null});


        //FOURS.add(new Player[] {null, FIRST, FIRST, FIRST, FIRST, null});
        FIRSTFOURS.add(new Player[]{FIRST, FIRST, FIRST, null, FIRST});
        FIRSTFOURS.add(new Player[]{FIRST, FIRST, null, FIRST, FIRST});
        FIRSTFOURS.add(new Player[]{FIRST, null, FIRST, FIRST, FIRST});
        FIRSTFOURS.add(new Player[]{FIRST, FIRST, FIRST, FIRST, null});
        FIRSTFOURS.add(new Player[]{null, FIRST, FIRST, FIRST, FIRST});

        REFUTATIONS1.addAll(FIRSTFOURS);


        FIRSTFOURS.add(new Player[]{FIRST, null, FIRST, FIRST, null});

        FIRSTFOURS2.addAll(FIRSTFOURS);

        FIRSTFOURS.add(new Player[]{null, FIRST, FIRST, FIRST, null});

        /*FIRSTFOURS2.add(new Player[]{FIRST, FIRST, FIRST, FIRST, null});
        FIRSTFOURS2.add(new Player[]{null, FIRST, FIRST, FIRST, FIRST});*/

        SECONDFOURS.add(new Player[]{SECOND, SECOND, SECOND, null, SECOND});
        SECONDFOURS.add(new Player[]{SECOND, SECOND, null, SECOND, SECOND});
        SECONDFOURS.add(new Player[]{SECOND, null, SECOND, SECOND, SECOND});
        SECONDFOURS.add(new Player[]{SECOND, SECOND, SECOND, SECOND, null});
        SECONDFOURS.add(new Player[]{null, SECOND, SECOND, SECOND, SECOND});

        REFUTATIONS2.addAll(SECONDFOURS);

        SECONDFOURS.add(new Player[]{SECOND, null, SECOND, SECOND, null});

        SECONDFOURS2.addAll(SECONDFOURS);

        SECONDFOURS.add(new Player[]{null, SECOND, SECOND, SECOND, null});



        /*SECONDFOURS2.add(new Player[]{SECOND, SECOND, SECOND, SECOND, null});
        SECONDFOURS2.add(new Player[]{null, SECOND, SECOND, SECOND, SECOND});*/

        //FOURS.add(new Player[] {null, FIRST, FIRST, FIRST, FIRST, null});

      /*  REFUTATIONS1.add(new Player[] {FIRST, FIRST, FIRST, FIRST, null, null});
        REFUTATIONS1.add(new Player[] {FIRST, FIRST, FIRST, null, FIRST, null});
        REFUTATIONS1.add(new Player[] {FIRST, FIRST, null, FIRST, FIRST, null});
        REFUTATIONS1.add(new Player[] {FIRST, null, FIRST, FIRST, FIRST, null});
        REFUTATIONS1.add(new Player[] {null, FIRST, FIRST, FIRST, FIRST, null});
        REFUTATIONS1.add(new Player[] {null, null, FIRST, FIRST, FIRST, FIRST});

        REFUTATIONS1.add(new Player[] {FIRST, null, FIRST, FIRST, FIRST, SECOND});
        REFUTATIONS1.add(new Player[] {FIRST, FIRST, null, FIRST, FIRST, SECOND});
        REFUTATIONS1.add(new Player[] {FIRST, FIRST, FIRST, FIRST, null, SECOND});
        REFUTATIONS1.add(new Player[] {SECOND, FIRST, FIRST, FIRST, FIRST, null});
        REFUTATIONS1.add(new Player[] {SECOND, FIRST, FIRST, null, FIRST, FIRST});
        REFUTATIONS1.add(new Player[] {SECOND, null, FIRST, FIRST, FIRST, FIRST});
        REFUTATIONS1.add(new Player[] {SECOND, FIRST, null, FIRST, FIRST, FIRST});
        REFUTATIONS1.add(new Player[] {SECOND, FIRST, FIRST, FIRST, null, FIRST});


        REFUTATIONS2.add(new Player[] {SECOND, SECOND, SECOND, SECOND, null, null});
        REFUTATIONS2.add(new Player[] {SECOND, SECOND, null, SECOND, SECOND, null});
        REFUTATIONS2.add(new Player[] {null, SECOND, SECOND, SECOND, SECOND, null});
        REFUTATIONS2.add(new Player[] {SECOND, null, SECOND, SECOND, SECOND, null});
        REFUTATIONS2.add(new Player[] {SECOND, SECOND, SECOND, SECOND, null, FIRST});
        REFUTATIONS2.add(new Player[] {SECOND, SECOND, null, SECOND, SECOND, FIRST});
        REFUTATIONS2.add(new Player[] {FIRST, SECOND, SECOND, SECOND, SECOND, null});
        REFUTATIONS2.add(new Player[] {SECOND, null, SECOND, SECOND, SECOND, FIRST});*/
    }

    public int matchPattern(Player[] pattern, Player player, boolean block) {
        int sum = 0;
        if (block) {
            if (player == FIRST) {
                for (Player[] a : FIRSTFOURS) {
                    if (sum == 5) break;
                    sum = 0;
                    for (int i = 0; i < a.length; i++) {
                        if (a[i] == pattern[i]) sum++;
                        if (sum >= 5) break;
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
                        if (sum >= 5) break;
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
                        if (sum >= 5) break;
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
                        if (sum >= 5) break;
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

    public int matchPattern2(Player[] pattern, Player player, boolean block) {
        int sum = 0;
        if (block) {
            if (player == FIRST) {
                for (Player[] a : THREES) {
                    if (sum == 6) break;
                    sum = 0;
                    for (int i = 0; i < a.length; i++) {
                        if (a[i] == pattern[i]) sum++;
                        if (sum >= 6) break;
                    }
                }
                if (sum == 6) {
                    for (int i = 0; i < 4; i++)
                        if (pattern[i] == FIRST && pattern[i + 1] == null && pattern[i + 2] == FIRST)
                            return i + 1;
                }
                sum = 0;
                for (Player[] a : SPECIFIC_THREES) {
                    if (sum == 6) break;
                    sum = 0;
                    for (int i = 0; i < a.length; i++) {
                        if (a[i] == pattern[i]) sum++;
                        if (sum >= 6) break;
                    }
                }
                if (sum == 6) {
                    boolean firstNull = false;
                    for (int i = 0; i < 6; i++) {
                        if (pattern[i] == null && firstNull)
                            return i;
                        if (pattern[i] == null) firstNull = true;
                    }
                }
            }

            if (player == SECOND) {
                for (Player[] a : THREES2) {
                    if (sum == 6) break;
                    sum = 0;
                    for (int i = 0; i < a.length; i++) {
                        if (a[i] == pattern[i]) sum++;
                        if (sum >= 6) break;
                    }
                }
                if (sum == 6) {
                    for (int i = 0; i < 6; i++) {
                        if (pattern[i] == SECOND && pattern[i + 1] == null && pattern[i + 2] == SECOND)
                            return i + 1;
                    }
                }
                sum = 0;
                for (Player[] a : SPECIFIC_THREES2) {
                    if (sum == 6) break;
                    sum = 0;
                    for (int i = 0; i < a.length; i++) {
                        if (a[i] == pattern[i]) sum++;
                        if (sum >= 6) break;
                    }
                }
                if (sum == 6) {
                    boolean firstNull = false;
                    for (int i = 0; i < 6; i++) {
                        if (pattern[i] == null && firstNull)
                            return i;
                        if (pattern[i] == null) firstNull = true;
                    }
                }
            }
        }
        return -1;
    }

    public double valuePattern(Player[] sequence, Player player) {
        int sum = 0;
        if (player == FIRST) {
            for (Player[] a : REFUTATIONS1) {
                if (sum == 5) break;
                sum = 0;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == sequence[i]) sum++;
                    if (sum >= 5) break;
                }
            }
            if (sum == 5) {
                return STRAIGHT_FOUR_POINTS;
            } else {
                for (int i = 0; i < 2; i++) {
                    if (sequence[i] == null && sequence[i + 1] == FIRST && sequence[i + 2] == FIRST && sequence[i + 3] == null)
                        return TWOS_POINTS;
                    if ((sequence[i] == null && sequence[i + 1] == FIRST && sequence[i + 2] == null && sequence[i + 3] == null) ||
                            (sequence[i] == null && sequence[i + 1] == null && sequence[i + 2] == FIRST && sequence[i + 3] == null))
                        return ONES_POINTS;
                }
            }
            /*for (Player[] a : FIRSTFOURS) {
                if (sum == 5) break;
                sum = 0;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == sequence[i]) sum++;
                    if (sum >= 5) break;
                }
            }
            if (sum == 5) {
                return 2 * THREES_POINTS;
            }*/
        }

        if (player == SECOND) {
            for (Player[] a : REFUTATIONS2) {
                if (sum == 5) break;
                sum = 0;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == sequence[i]) sum++;
                    if (sum >= 5) break;
                }
            }
            if (sum == 5) {
                return STRAIGHT_FOUR_POINTS;
            }
            for (int i = 0; i < 2; i++) {
                if (sequence[i] == null && sequence[i + 1] == SECOND && sequence[i + 2] == SECOND && sequence[i + 3] == null)
                    return TWOS_POINTS;
                if ((sequence[i] == null && sequence[i + 1] == SECOND && sequence[i + 2] == null && sequence[i + 3] == null) ||
                        sequence[i] == null && sequence[i + 1] == null && sequence[i + 2] == SECOND && sequence[i + 3] == null)
                    return ONES_POINTS;
            }
            /*for (Player[] a : SECONDFOURS) {
                if (sum == 5) break;
                sum = 0;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == sequence[i]) sum++;
                    if (sum >= 5) break;
                }
            }
            if (sum == 5) {
                return 2 * THREES_POINTS;
            }*/
        }
        return 0;
    }

    public double valuePattern2(Player[] sequence, Player player) {
        int sum = 0;
        if (player == FIRST) {
            for (Player[] a : THREES) {
                if (sum == 6) break;
                sum = 0;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == sequence[i]) sum++;
                    if (sum >= 6) break;
                }
            }
            if (sum == 6) {
                return THREES_POINTS;
            }

            for (Player[] a : MIXEDTHREES) {
                if (sum == 6) break;
                sum = 0;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == sequence[i]) sum++;
                    if (sum >= 6) break;
                }
            }
            if (sum == 6) {
                return MIXED_THREES_POINTS;
            }
            for (int i = 0; i < 3; i++) {
                if (sequence[i] == null && sequence[i + 1] == FIRST && sequence[i + 2] == FIRST && sequence[i + 3] == null)
                    return TWOS_POINTS;
                if (sequence[i] == null && sequence[i + 1] == FIRST && sequence[i + 2] == null && sequence[i + 3] == null)
                    return ONES_POINTS;
            }
        }

        if (player == SECOND) {
            for (Player[] a : THREES2) {
                if (sum == 6) break;
                sum = 0;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == sequence[i]) sum++;
                    if (sum >= 6) break;
                }
            }
            if (sum == 6) {
                for (int i = 0; i < 6; i++) {
                    return THREES_POINTS;
                }
            }
            for (Player[] a : MIXEDTHREES2) {
                if (sum == 6) break;
                sum = 0;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == sequence[i]) sum++;
                    if (sum >= 6) break;
                }
            }
            if (sum == 6) {
                return MIXED_THREES_POINTS;
            }
            for (int i = 0; i < 3; i++) {
                if (sequence[i] == null && sequence[i + 1] == FIRST && sequence[i + 2] == FIRST && sequence[i + 3] == null)
                    return TWOS_POINTS;
                if (sequence[i] == null && sequence[i + 1] == FIRST && sequence[i + 2] == null && sequence[i + 3] == null)
                    return ONES_POINTS;
            }
        }
        return -1;
    }

}
