package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.Picnic;
import com.ajegames.picnic.Player;
import com.ajegames.picnic.repository.KeyGenerator;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created for AJE Games by bigdaddy on 6/22/13 at 11:57 AM.
 */
@Path("/picnic/spinner")
public class SpinnerResource extends BasePicnicResource {

  private static Logger LOG = LoggerFactory.getLogger(SpinnerResource.class);
  private static Map<String, Picnic> pendingSpins = Maps.newHashMap();

  @GET
  @PathParam("/{gameKey}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getSpinToken(@PathParam("gameKey") String gameKey,
                             @QueryParam("playerKey") String playerKey) {
    LOG.info("Invoked getSpinToken with gameKey=" + gameKey + " and playerKey=" + playerKey);
    Picnic game = getGame(gameKey);
    Player player = getPlayer(playerKey);
    LOG.warn("This is not yet implemented correctly.");
    return KeyGenerator.generateKey(8);
  }

/*
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Item> getSpinnerOptions() {
    return PicnicSpinner.getSpinnerOptions();
  }
*/

  @POST
  @PathParam("/{spinToken}")
  @Produces(MediaType.APPLICATION_JSON)
  public String spin(@PathParam("spinToken") String spinToken) {
    LOG.info("Invoked spin with spinToken=" + spinToken);
    LOG.warn("This is not yet implemented correctly.");
    // TODO use spin token to access the game and spin the correct spinner
    return consumeSpinToken(spinToken);
  }

  /**
   * Returns spin token for given player if that player's turn is up.  Returns null if not player's turn.
   *
   * @param playerKey
   * @return
   */
  private String issueSpinToken(String playerKey) {
    return null;
  }

  /**
   * Uses spin token and returns the spin results.
   *
   * @param token
   * @return
   */
  private String consumeSpinToken(String token) {
    return null;
  }

}
