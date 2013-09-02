package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.domain.Picnic;
import com.ajegames.picnic.domain.Player;
import com.ajegames.picnic.repository.GameRepository;
import com.ajegames.picnic.repository.PlayerRepository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by dave on 7/14/13 at 11:15 AM.
 */
abstract public class BasePicnicResource {

    /* helper methods */

  protected Player getPlayer(String playerKey) {
    Player player = PlayerRepository.findPlayer(playerKey);
    if (player == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return player;
  }

  protected Picnic getGame(String gameKey) {
    Picnic game = GameRepository.findGame(gameKey);
    if (game == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return game;
  }

}
