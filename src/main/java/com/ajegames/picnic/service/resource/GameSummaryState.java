package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.domain.Picnic;

public class GameSummaryState {

  private Picnic game;

  public GameSummaryState(Picnic game) {
    this.game = game;
  }

  public String getGameKey() {
    return game.getKey();
  }

  public String getOrganizerKey() {
    return game.getOrganizer().getKey();
  }

  public String getOrganizer() {
    return game.getOrganizer().getName();
  }
}
