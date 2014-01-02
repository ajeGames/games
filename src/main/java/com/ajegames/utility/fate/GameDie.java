/*
 * Copyright (c) 2012. by ajeGames.  All rights reserved.
 */

package com.ajegames.utility.fate;

public class GameDie implements Comparable<GameDie> {

  private int sides = 0;
  private int value = 0;

  public GameDie(int sides) {
    this.sides = sides;
    this.roll();
  }

  public int roll() {
    value = new Double(Math.random() * sides).intValue() + 1;
    return value;
  }

  public int getSides() {
    return sides;
  }

  public int getValue() {
    return this.value;
  }

  @Override
  public int compareTo(GameDie arg0) {
    return this.value == arg0.value ? 0 : this.value < arg0.value ? -1 : 1;
  }

  public String toString() {
    return "Sides:" + sides + " (Rolled:" + value + ")  ";
  }
}
