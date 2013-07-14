package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.Picnic;
import com.ajegames.picnic.Player;
import com.ajegames.picnic.repository.GameRepository;
import com.yammer.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/picnic/game")
public class GameResource extends BasePicnicResource {

  private static Logger LOG = LoggerFactory.getLogger(GameResource.class);

  @GET
  @Path("{gameKey}")
  @Produces(MediaType.APPLICATION_JSON)
  @Timed
  public GameState showGame(@PathParam("gameKey") String gameKey) {
    LOG.info("Invoked showGame with gameKey=" + gameKey);
    Picnic game = GameRepository.findGame(gameKey);
    if (game == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return buildGameState(game);
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  @Timed
  public GameState startGame(@FormParam("playerKey") String playerKey) {
    LOG.info("Invoked startGame with playerKey=" + playerKey);
    Player player = getPlayer(playerKey);
    Picnic game = GameRepository.createGame();
    if (game == null) {
      LOG.error("Just created game; should not be null.");
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
    game.addPlayer(player);
    return buildGameState(game);
  }

  @PUT
  @Path("{gameKey}")
  @Produces(MediaType.APPLICATION_JSON)
  @Timed
  public GameState joinGame(@PathParam("gameKey") String gameKey,
                         @FormParam("playerKey") String playerKey) {
    LOG.info("Invoked joinGame with gameKey=" + gameKey + " and playerKey=" + playerKey);
    Picnic game = getGame(gameKey);
    Player player = getPlayer(playerKey);
    game.addPlayer(player);
    return buildGameState(game);
  }

  private GameState buildGameState(Picnic game) {
    return new GameState(game);
  }
}
