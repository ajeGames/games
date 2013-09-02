package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.domain.Picnic;
import com.ajegames.picnic.domain.Player;
import com.google.common.collect.Lists;

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

  public String getCurrentPlayer() {
    return game.getCurrentPlayer().getKey();
  }

/*
  public List<Player> getPlayers() {
    return game.getPlayers();
  }
*/

  public List<PlayerGameState> getPlayers() {
    List<PlayerGameState> playerStates = Lists.newArrayList();
    for (Player pl : game.getPlayers()) {
      playerStates.add(new PlayerGameState(game, pl));
    }
    return playerStates;
  }

  public String getStatus() {
    return game.getStatus().toString();
  }
}
