/*
 * Copyright (c) 2012. by ajeGames.  All rights reserved.
 */

package com.ajegames.utility;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Utility methods for working with dice. Assumes that dice are rolled as soon as they
 * are added to the collection; that is, the value of a die is never undetermined.
 *
 * @author bubbabubbagump
 */
public class Dice {

  private ArrayList<GameDie> myDice;

  private Dice() {
    myDice = new ArrayList<GameDie>();
  }

  public static Dice createSingle(int sides) {
    return new Dice().add(sides);
  }

  public static Dice createDice(int[] sides) {
    Dice dice = new Dice();
    dice.addAll(sides);
    return dice;
  }

  /**
   * Adds a single die with given number of sides.
   *
   * @param sides number of sides for the additional die
   * @return instance of Dice to make it easy to add more
   */
  public Dice add(int sides) {
    if (sides < 4) {
      throw new RuntimeException("Attempted to create die with invalid number of sides");
    }
    myDice.add(new GameDie(sides));
    return this;
  }

  /**
   * Adds one die for each in the array of sides.
   *
   * @param sides array of number of sides for each additional die
   * @return instance of Dice to make it easy to add more
   */
  public Dice addAll(int[] sides) {
    for (int side : sides) {
      add(side);
    }
    return this;
  }

  /**
   * Returns maximum possible value of dice.
   *
   * @return total sides of all dice
   */
  public int getMaximum() {
    return totalSides();
  }

  /**
   * Returns minimum possible value of dice.
   *
   * @return number of dice
   */
  public int getMinimum() {
    return myDice.size();
  }

  /**
   * Rolls the dice and returns the sum of values.
   *
   * @return sum of all dice
   */
  public Dice roll() {
    for (GameDie die : myDice) {
      die.roll();
    }
    return this;
  }

  /**
   * Returns result of previous roll.  Dice are expected to be in determinate state
   * (i.e., have a value) from the moment they are added to the collection.
   *
   * @return value of previous roll
   */
  public int getTotal() {
    return totalDice();
  }

  /**
   * Calculates value as a percentage of the highest possible value given the dice.
   *
   * @return value as percentage of total possible value
   */
/*
  public double getPercentageValue() {
    return (double) totalDice() / (double) totalSides();
  }
*/
  private int totalDice() {
    int total = 0;
    for (GameDie die : myDice) {
      total += die.getValue();
    }
    return total;
  }

  private int totalSides() {
    int total = 0;
    for (GameDie die : myDice) {
      total += die.getSides();
    }
    return total;
  }

  public void show() {
    System.out.println(toString());
  }

  public int getTotalOfTop(int howManyToChoose) {
    int topTotal = 0;
    if (howManyToChoose > myDice.size()) {
      throw new RuntimeException("Asked to sum more dice than available");
    } else if (howManyToChoose == myDice.size()) {
      return getTotal();
    }

    // not thread safe; would be better to copy to new immutable sorted list
    Collections.sort(myDice);
    for (int i = myDice.size() - howManyToChoose; i < myDice.size(); i++) {
      topTotal += myDice.get(i).getValue();
    }
    return topTotal;
  }

  public String toString() {
    StringBuilder out = new StringBuilder();
    for (GameDie die : myDice) {
      out.append(die.toString());
    }
    return out.toString();
  }
}
