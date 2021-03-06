package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.domain.Picnic;
import com.ajegames.picnic.domain.SpinStatus;
import com.ajegames.picnic.repository.KeyGenerator;
import com.google.common.collect.Maps;
import com.yammer.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/picnic/spinner")
public class SpinnerResource extends BasePicnicResource {

  private static Logger LOG = LoggerFactory.getLogger(SpinnerResource.class);
  private static Map<String, Picnic> pendingSpins = Maps.newHashMap();

  private String createSpinToken(Picnic game) {
    String spinToken = KeyGenerator.generateKey(8);
    pendingSpins.put(spinToken, game);
    return spinToken;
  }

  private Picnic consumeSpinToken(String spinToken) {
    return pendingSpins.remove(spinToken);
  }

  @GET
  @Path("{gameKey}")
  @Produces(MediaType.APPLICATION_JSON)
  @Timed
  public SpinState getSpinToken(@PathParam("gameKey") String gameKey,
                                @QueryParam("playerKey") String playerKey) {
    LOG.info("Invoked getSpinToken with gameKey=" + gameKey + " and playerKey=" + playerKey);
    Picnic game = getGame(gameKey);
    if (!game.isPlaying()) {
      LOG.warn("Someone tried to get a spin token for game that is " + (game.isGameOver() ? "over." : "in staging."));
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
    if (!game.isCurrentPlayer(playerKey)) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return SpinState.createForToken(createSpinToken(game));
  }

/*
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Item> getSpinnerOptions() {
    return PicnicSpinner.getSpinnerOptions();
  }
*/

  @POST
  @Path("{spinToken}")
  @Produces(MediaType.APPLICATION_JSON)
  @Timed
  public SpinState spin(@PathParam("spinToken") String spinToken) {
    LOG.info("Invoked spin with spinToken=" + spinToken);
    Picnic game = consumeSpinToken(spinToken);
    if (game == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    SpinStatus result = game.takeTurn();
    SpinState state = SpinState.createForSpin(result);
    if (result.isSpinAgain()) {
      state.setSpinToken(createSpinToken(game));
    }
    return state;
  }

}
