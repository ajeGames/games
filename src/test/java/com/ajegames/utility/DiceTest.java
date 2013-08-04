/*
 * Copyright (c) 2012. by ajeGames.  All rights reserved.
 */

package com.ajegames.utility;

import org.testng.annotations.Test;

public class DiceTest {

  @Test
  public void testImpossibleDice() {
    int[] sidesToTry = new int[]{-4, 0, 3};
    for (int sides : sidesToTry) {
      try {
        Dice.createSingle(sides);
      } catch (Exception e) {
        continue;
      }
      assert false;  // should never reach this line
    }
  }

  @Test
  public void testCreateOneDie() {
    Dice myDice = Dice.createSingle(4);
    int value = myDice.getTotal();
    myDice.show();
    assert myDice.getMaximum() == 4 : "Expected 4 sides";
    assert myDice.getMinimum() == 1 : "Expected 1 die";
    assert 0 < value && value < 5 : "Return value out of bounds";
  }

  @Test
  public void testCreateTwoDice() {
    Dice myDice = Dice.createDice(new int[]{6, 6});
    myDice.show();
    assert myDice.getMaximum() == 12 : "Expected 12 sides";
  }

  @Test
  public void testGetMinimumGetMaximum() {
    Dice myDice = Dice.createDice(new int[]{4, 5, 6});
    assert myDice.getMinimum() == 3 : "Expected 3";
    assert myDice.getMaximum() == 15 : "Expected 15 sides";
    myDice.add(10);
    assert myDice.getMinimum() == 4 : "Expected 4";
    assert myDice.getMaximum() == 25 : "Expected 25 sides";
    myDice.show();
  }

  @Test
  public void testGetTotalOfTop() {

    Dice setOfOne = Dice.createDice(new int[]{6});
    Dice setOfTwo = Dice.createDice(new int[]{4, 4});
    Dice setOfFour = Dice.createDice(new int[]{10, 10, 10, 10});
    Dice setOfSix = Dice.createDice(new int[]{6, 6, 6, 6, 6, 6});

    // bound to fail if not working properly
    for (int i = 0; i < 100; i++) {
      assert setOfOne.roll().getTotalOfTop(1) <= 6;
      assert setOfTwo.roll().getTotalOfTop(1) <= 4;
      assert setOfFour.roll().getTotalOfTop(2) <= 20;
      assert setOfSix.roll().getTotalOfTop(2) <= 12;
    }
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testGetTotalOfTopFailsAppropriately() {
    Dice.createDice(new int[]{6}).getTotalOfTop(2);
  }

  @Test
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
