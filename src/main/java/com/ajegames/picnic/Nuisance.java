package com.ajegames.picnic;

/**
 * Things that work against the objective of having a picnic.
 */
public class Nuisance extends Item {

  private Item worksAgainst;
  private ItemType worksAgainstType;
  private boolean wipeOut;

  private Nuisance(String name) {
    super(name, ItemType.NUISANCE);
  }

  @Override
  public boolean isNuisance() {
    return true;
  }

  public static Nuisance create(String name) {
    return new Nuisance(name);
  }

  public static Nuisance createWipeOut(String name) {
    Nuisance newNuisance = create(name);
    newNuisance.wipeOut = true;
    return newNuisance;
  }

  public static Nuisance createAgainstItem(String name, Item itemToCounteract) {
    Nuisance newNuisance = new Nuisance(name);
    newNuisance.setWorksAgainst(itemToCounteract);
    return newNuisance;
  }

  public static Nuisance createAgainstFood(String name) {
    Nuisance newNuisance = new Nuisance(name);
    newNuisance.setWorksAgainstType(ItemType.FOOD);
    return newNuisance;
  }

  public static Nuisance createAgainstDrink(String name) {
    Nuisance newNuisance = new Nuisance(name);
    newNuisance.setWorksAgainstType(ItemType.DRINK);
    return newNuisance;
  }

  public static Nuisance createAgainstSupply(String name) {
    Nuisance newNuisance = new Nuisance(name);
    newNuisance.setWorksAgainstType(ItemType.SUPPLY);
    return newNuisance;
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
}
