package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.Player;
import com.ajegames.picnic.repository.PlayerRepository;
import com.yammer.metrics.annotation.Timed;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created for AJE Games by bigdaddy on 5/4/13 at 4:30 PM.
 */
@Path("/picnic/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource extends BasePicnicResource {

  // TODO return player document, which will be more complex than just player data; include resource URIs

  @GET
  @PathParam("/{playerKey}")
  public Player getPlayer(@PathParam("playerKey") String playerKey) {
    return getPlayer(playerKey);
  }

  @POST
  @Timed
  public Player createPlayer(@FormParam("playerName") String name) {
    if (name == null) {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
    return PlayerRepository.createPlayer(name);
  }
}
