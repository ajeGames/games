/*
 * Copyright (c) 2012. by ajeGames.  All rights reserved.
 */

package com.ajegames.dungeons.world.player;

public class PlayerAbilities {

  private int strength;
  private int intelligence;
  private int wisdom;
  private int dexterity;
  private int constitution;
  private int charisma;

  public static PlayerAbilities createPlayerAbilities(int strength, int intelligence, int wisdom, int dexterity,
                                                      int constitution, int charisma) {
    PlayerAbilities out = new PlayerAbilities();
    out.strength = strength;
    out.intelligence = intelligence;
    out.wisdom = wisdom;
    out.dexterity = dexterity;
    out.constitution = constitution;
    out.charisma = charisma;
    return out;
  }

  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getIntelligence() {
    return intelligence;
  }

  public void setIntelligence(int intelligence) {
    this.intelligence = intelligence;
  }

  public int getWisdom() {
    return wisdom;
  }

  public void setWisdom(int wisdom) {
    this.wisdom = wisdom;
  }

  public int getDexterity() {
    return dexterity;
  }

  public void setDexterity(int dexterity) {
    this.dexterity = dexterity;
  }

  public int getConstitution() {
    return constitution;
  }

  public void setConstitution(int constitution) {
    this.constitution = constitution;
  }

  public int getCharisma() {
    return charisma;
  }

  public void setCharisma(int charisma) {
    this.charisma = charisma;
  }

  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("Player Abilities:")
            .append("\n Strength: ").append(strength)
            .append("\n Intelligence: ").append(intelligence)
            .append("\n Wisdom: ").append(wisdom)
            .append("\n Dexterity: ").append(dexterity)
            .append("\n Constitution: ").append(constitution)
            .append("\n Charisma: ").append(charisma);
    return out.toString();
  }
}
