package com.capgemini.algorithmbattles.ballsapp.logic.model;

import java.util.Objects;

public class BoardCell {

  private int x;
  private int y;
  private Player player;

  public BoardCell(int x, int y, Player player) {
    this.x = x;
    this.y = y;
    this.player = player;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;

  }
}
