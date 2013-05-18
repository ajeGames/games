package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.Player;
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
    return new Player(name);
  }
}
