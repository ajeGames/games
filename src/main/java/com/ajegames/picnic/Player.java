package com.ajegames.picnic;

/**
 * Someone playing Picnic.  Player holds a basket that must be filled sufficiently to win.
 */
public class Player {

  private long id;
  private final String name;
  private Basket basket;

  public Player(String playerName) {
    name = playerName;
    basket = new Basket();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
