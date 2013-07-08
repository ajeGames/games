package com.ajegames.picnic;

/**
 * Things that work against the objective of having a picnic.
 */
public class Nuisance extends Item {

  private Item worksAgainst;
  private ItemType worksAgainstType;
  private boolean wipeOut;
  private boolean loseTurn;

  private Nuisance(String key, String description) {
    super(key, description, ItemType.NUISANCE);
  }

  /*
    === creation methods ===
   */

  public static Nuisance createWipeOut(String key, String description) {
    Nuisance newNuisance = new Nuisance(key, description);
    newNuisance.wipeOut = true;
    return newNuisance;
  }

  public static Nuisance createWipeOut(String key) {
    return createWipeOut(key, key);
  }

  public static Nuisance createLoseTurn(String key, String description) {
    Nuisance newNuisance = new Nuisance(key, description);
    newNuisance.loseTurn = true;
    return newNuisance;
  }

  public static Nuisance createLoseTurn(String key) {
    return createLoseTurn(key, key);
  }

  public static Nuisance createAgainstItem(String key, String description, Item itemToCounteract) {
    Nuisance newNuisance = new Nuisance(key, description);
    newNuisance.setWorksAgainst(itemToCounteract);
    return newNuisance;
  }

  public static Nuisance createAgainstItem(String key, Item itemToCounteract) {
    return createAgainstItem(key, key, itemToCounteract);
  }

  private static Nuisance createAgainstType(String key, String description, ItemType type) {
    Nuisance newNuisance = new Nuisance(key, description);
    newNuisance.setWorksAgainstType(type);
    return newNuisance;
  }

  public static Nuisance createAgainstFood(String key, String description) {
    return createAgainstType(key, description, ItemType.FOOD);
  }

  public static Nuisance createAgainstDrink(String key, String description) {
    return createAgainstType(key, description, ItemType.DRINK);
  }

  public static Nuisance createAgainstSupply(String key, String description) {
    return createAgainstType(key, description, ItemType.SUPPLY);
  }

  public static Nuisance createAgainstFood(String key) {
    return createAgainstFood(key, key);
  }

  public static Nuisance createAgainstDrink(String key) {
    return createAgainstDrink(key, key);
  }

  public static Nuisance createAgainstSupply(String key) {
    return createAgainstSupply(key, key);
  }

  /*
    === Instance methods ===
   */

  @Override
  public boolean isNuisance() {
    return true;
  }

  private void setWorksAgainst(Item worksAgainst) {
    this.worksAgainst = worksAgainst;
  }

  public Item getWorksAgainst() {
    return worksAgainst;
  }

  private void setWorksAgainstType(ItemType worksAgainstType) {
    this.worksAgainstType = worksAgainstType;
  }

  public ItemType getWorksAgainstType() {
    return worksAgainstType;
  }

  public boolean isAgainstItem() {
    return worksAgainst != null;
  }

  public boolean isAgainstItemType() {
    return worksAgainstType != null;
  }

  public boolean isWipeOut() {
    return wipeOut;
  }

  public boolean isLoseTurn() {
    return loseTurn;
  }
}
