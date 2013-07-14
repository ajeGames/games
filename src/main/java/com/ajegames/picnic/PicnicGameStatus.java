package com.ajegames.picnic;

public enum PicnicGameStatus {
  STAGING ("Staging"),
  PLAYING ("Playing"),
  GAME_OVER ("Game over");

  private final String description;

  PicnicGameStatus(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
