package com.ajegames.picnic;

import com.ajegames.picnic.repository.PersistedGameEntity;

/**
 * Someone playing Picnic.  Player holds a basket that must be filled sufficiently to win.
 */
public class Player implements PersistedGameEntity {

  private String key;
  private String name;

  public static Player createPlayer(String key, String playerName) {
    return new Player(key, playerName);
  }

  private Player(String key, String playerName) {
    this.key = key;
    this.name = playerName;
  }

  public String getKey() {
    return key;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getName() + ":" + getKey();
  }
}
