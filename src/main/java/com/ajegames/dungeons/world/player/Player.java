package com.ajegames.dungeons.world.player;

/**
 * Encapsulates everything to do with a player character.
 */
public class Player {

  private Long id;
  private String name;
  private String race;
  private String profession;
  private int strength;
  private int intelligence;
  private int wisdom;
  private int dexterity;
  private int constitution;
  private int charisma;

  /**
   * For Hibernate
   */
  public Player() {
  }

  public static Player createNewPlayer(String name, String race, String profession, int strength, int intelligence,
                                       int wisdom, int dexterity, int constitution, int charisma) {
    Player out = new Player();
    out.name = name;
    out.race = race;
    out.profession = profession;
    out.strength = strength;
    out.intelligence = intelligence;
    out.wisdom = wisdom;
    out.dexterity = dexterity;
    out.constitution = constitution;
    out.charisma = charisma;
    return out;
  }

  public Long getId() {
    return id;
  }

  private void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRace() {
    return race;
  }

  public void setRace(String race) {
    this.race = race;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
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
}
