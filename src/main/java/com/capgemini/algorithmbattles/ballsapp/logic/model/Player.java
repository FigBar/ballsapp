package com.capgemini.algorithmbattles.ballsapp.logic.model;

/**
 * The number of the player determines the order of them in the game.
 */
public enum Player {

  /**
   * This player is the first player to make a move.
   */
  PLAYER_1,
  /**
   * This player is the second player to make a move.
   */
  PLAYER_2;

  public String toString() {
    return this.equals(PLAYER_1) ? "player1" : "player2";
  }

  public int getIndex(){
    return this.equals(PLAYER_1) ? 1 : 2;
  }

  public static Player mapToPlayer(String player) {
    return "player1".equals(player) ? PLAYER_1 : PLAYER_2;
  }

  public Player getOther(){
    return "player1".equals(this.toString()) ? PLAYER_2 : PLAYER_1;
  }

}
