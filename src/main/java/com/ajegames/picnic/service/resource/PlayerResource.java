package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.Player;
import com.ajegames.picnic.repository.PlayerRepository;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created for AJE Games by bigdaddy on 5/4/13 at 4:30 PM.
 */
@Path("/picnic/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

  @POST
  @Timed
  public Player createPlayer(@FormParam("playerName") String name) {
    PlayerRepository playerRepo = PlayerRepository.getInstance();
    Player player = new Player(name);
    playerRepo.addPlayer(player);
    return player;
  }
}
