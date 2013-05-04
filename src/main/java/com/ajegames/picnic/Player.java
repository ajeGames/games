package com.ajegames.picnic;

/**
 * Someone playing Picnic.  Player holds a basket that must be filled sufficiently to win.
 */
public class Player {

  private final String name;
  private Basket basket;

  public Player(String playerName) {
    name = playerName;
    basket = new Basket();
  }

  public String getName() {
    return name;
  }

  public void gatherItem(Item item) {
    basket.gatherItem(item);
  }

  public Basket getBasket() {
    return basket;
  }

  @Override
  public String toString() {
    return name;
  }
}
