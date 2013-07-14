package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.Picnic;
import com.ajegames.picnic.Player;

import java.util.List;

/**
 * Presents a clean fascade to the game for easy mapping to JSON.
 */
public class GameState {

  private Picnic game;

  public GameState(Picnic game) {
    this.game = game;
  }

  public String getKey() {
    return game.getKey();
  }

  public List<Player> getPlayers() {
    return game.getPlayers();
  }

  public String getStatus() {
    return game.getStatus().toString();
  }
}
