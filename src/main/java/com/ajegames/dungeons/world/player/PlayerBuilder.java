/*
 * Copyright (c) 2012. by ajeGames.  All rights reserved.
 */

package com.ajegames.dungeons.world.player;

import com.ajegames.utility.Dice;

public class PlayerBuilder {

  public static PlayerAbilities createPlayer(int[] scores) {

    PlayerAbilities hero = new PlayerAbilities();
    Dice playerDice = Dice.createDice(new int[]{6, 6, 6, 6, 6});
    hero.setStrength(playerDice.roll().getTotalOfTop(3));
    hero.setIntelligence(playerDice.roll().getTotalOfTop(3));
    hero.setWisdom(playerDice.roll().getTotalOfTop(3));
    hero.setDexterity(playerDice.roll().getTotalOfTop(3));
    hero.setConstitution(playerDice.roll().getTotalOfTop(3));
    hero.setCharisma(playerDice.roll().getTotalOfTop(3));

    System.out.println(hero.toString());
    return hero;
  }
}
