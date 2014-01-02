package com.ajegames.picnic.domain;

public enum PicnicStatus {
  STAGING ("Staging"),
  PLAYING ("Playing"),
  GAME_OVER ("Game over");

  private final String description;

  PicnicStatus(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
