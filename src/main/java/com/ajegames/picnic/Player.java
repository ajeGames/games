package com.ajegames.picnic;

import com.ajegames.picnic.repository.PersistedGameEntity;

/**
 * Someone playing Picnic.  Player holds a basket that must be filled sufficiently to win.
 */
public class Player implements PersistedGameEntity {

  private String key;
  private long id;
  private String name;
  private Basket basket;

  public static Player createPlayer(String key, long id, String playerName) {
    return new Player(key, id, playerName);
  }

  private Player(String key, long id, String playerName) {
    this.key = key;
    this.id = id;
    this.name = playerName;
    emptyBasket();
  }

  private void emptyBasket() {
    basket = new Basket();
  }

  public String getKey() {
    return key;
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
