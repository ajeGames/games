/*
 * Copyright (c) 2012. by ajeGames.  All rights reserved.
 */

package com.ajegames.dungeons.world.player;

import com.ajegames.utility.Dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Determines the abilities for a player character based on one of a predefined set of strategies.
 */
public class PlayerAbilityGenerator {

  /**
   * Generates scores using method I.
   *
   * @return List of scores to present to player for assignment to abilities
   */
  public static List<Integer> getScoresUsingMethodI() {
    Dice dice = Dice.createDice(new int[]{6, 6, 6, 6});
    List<Integer> rollValues = new ArrayList<Integer>();
    for (int i = 0; i < 6; i++) {
      rollValues.add(dice.roll().getTotalOfTop(3));
    }
    Collections.sort(rollValues);
    Collections.reverse(rollValues);
    return rollValues;
  }

  /**
   * Generates scores using method II.
   *
   * @return List of scores to present to player for assignment to abilities
   */
  public static List<Integer> getScoresUsingMethodII() {
    Dice dice = Dice.createDice(new int[]{6, 6, 6});
    List<Integer> rollValues = new ArrayList<Integer>();
    for (int i = 0; i < 12; i++) {
      int val = dice.roll().getTotal();
      rollValues.add(val);
    }
    Collections.sort(rollValues);
    Collections.reverse(rollValues);
    return rollValues.subList(0, 6);
  }

  /**
   * Generates scores using method III.
   *
   * @return List of scores to present to player
   */
  public static List<Integer> getScoresUsingMethodIII() {
    Dice dice = Dice.createDice(new int[]{6, 6, 6});
    List<Integer> abilityValues = new ArrayList<Integer>();
    for (int i = 0; i < 6; i++) {
      int max = 0;
      for (int j = 0; j < 6; j++) {
        int val = dice.roll().getTotal();
        if (val > max) {
          max = val;
        }
      }
      abilityValues.add(max);
    }
    return abilityValues;
  }

  /**
   * Generates scores using method IV.
   *
   * @return List of List of scores for player to choose
   */
  public static List<List<Integer>> getScoresUsingMethodIV() {
    Dice dice = Dice.createDice(new int[]{6, 6, 6});
    List<List<Integer>> abilitySets = new ArrayList<List<Integer>>();
    for (int i = 0; i < 12; i++) {
      List<Integer> abilities = new ArrayList<Integer>();
      abilitySets.add(abilities);
      for (int j = 0; j < 6; j++) {
        abilities.add(dice.roll().getTotal());
      }
    }
    return abilitySets;
  }

}
