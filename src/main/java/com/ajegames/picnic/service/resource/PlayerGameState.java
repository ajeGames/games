package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.domain.Basket;
import com.ajegames.picnic.domain.Picnic;
import com.ajegames.picnic.domain.Player;

/**
 * Created by dave on 7/14/13 at 3:43 PM.
 */
public class PlayerGameState {
  private Picnic game;
  private Player player;

  public PlayerGameState(Picnic game, Player player) {
    this.game = game;
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

  public BasketState getBasket() {
    Basket basket = game.getBasket(player.getKey());
    return new BasketState(basket);
  }
}
