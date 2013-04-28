/*
 * Copyright (c) 2012. by ajeGames.  All rights reserved.
 */

package com.ajegames.utility;

import junit.framework.TestCase;

public class DiceTest extends TestCase {

  public void testImpossibleDice() {
    int[] sidesToTry = new int[]{-4, 0, 3};
    for (int sides : sidesToTry) {
      try {
        Dice.createSingle(sides);
      } catch (Exception e) {
        continue;
      }
      fail("Allowed die with fewer than minimum number of sides.");
    }
  }

  public void testCreateOneDie() {
    Dice myDice = Dice.createSingle(4);
    int value = myDice.getTotal();
    myDice.show();
    assertEquals("Expected 4 sides", 4, myDice.getMaximum());
    assertEquals("Expected 1 die", 1, myDice.getMinimum());
    assertTrue("Return value out of bounds", value > 0 && value < 5);
  }

  public void testCreateTwoDice() {
    Dice myDice = Dice.createDice(new int[]{6, 6});
    myDice.show();
    assertEquals("Expected 12 sides", 12, myDice.getMaximum());
  }

  public void testGetMinimumGetMaximum() {
    Dice myDice = Dice.createDice(new int[]{4, 5, 6});
    assertEquals("Expected 3", 3, myDice.getMinimum());
    assertEquals("Expected 15 sides", 15, myDice.getMaximum());
    myDice.add(10);
    assertEquals("Expected 4", 4, myDice.getMinimum());
    assertEquals("Expected 25 sides", 25, myDice.getMaximum());
    myDice.show();
  }

  public void testGetTotalOfTop() {

    Dice setOfOne = Dice.createDice(new int[]{6});
    Dice setOfTwo = Dice.createDice(new int[]{4, 4});
    Dice setOfFour = Dice.createDice(new int[]{10, 10, 10, 10});
    Dice setOfSix = Dice.createDice(new int[]{6, 6, 6, 6, 6, 6});

    // bound to fail if not working properly
    for (int i = 0; i < 100; i++) {
      assertTrue(setOfOne.roll().getTotalOfTop(1) <= 6);
      assertTrue(setOfTwo.roll().getTotalOfTop(1) <= 4);
      assertTrue(setOfFour.roll().getTotalOfTop(2) <= 20);
      assertTrue(setOfSix.roll().getTotalOfTop(2) <= 12);
    }
  }

  public void testGetTotalOfTopFailsAppropriately() {
    try {
      Dice.createDice(new int[]{6}).getTotalOfTop(2);
    } catch (Exception e) {
      return;
    }
    fail("Supposed to throw if number of dice requested greater than number of dice.");
  }

  public void testRandomness() {
    tallyRolls(Dice.createDice(new int[]{4}), 1000);
    tallyRolls(Dice.createDice(new int[]{5}), 1000);
    tallyRolls(Dice.createDice(new int[]{20}), 1000);
    tallyRolls(Dice.createDice(new int[]{6, 6}), 1000);
    tallyRolls(Dice.createDice(new int[]{4, 6, 8}), 1000);
    tallyRolls(Dice.createDice(new int[]{6, 6, 6, 6, 6}), 100000);
  }

  private void tallyRolls(Dice trialDice, int rolls) {
    int[] tally = new int[trialDice.getMaximum() + 1];
    for (int i = 0; i < rolls; i++) {
      tally[trialDice.roll().getTotal() - 1]++;
    }
    System.out.print("" + trialDice.getMinimum() + " dice rolled " + rolls + " times => ");
    for (int j = 0; j < tally.length; j++) {
      System.out.print("" + (j + 1) + ":" + tally[j] + " ");
    }
    System.out.println();
  }
}
