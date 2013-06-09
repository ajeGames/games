package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.Picnic;
import com.ajegames.picnic.PicnicSpinner;
import com.ajegames.picnic.Player;
import com.ajegames.picnic.repository.GameRepository;
import com.ajegames.picnic.repository.PlayerRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created for AJE Games by bigdaddy on 5/5/13 at 8:54 PM.
 */
@Path("/picnic/game")
public class GameResource {

  @GET
  @PathParam("/{gameID}")
  @Produces(MediaType.APPLICATION_JSON)
  public Picnic getGame() {
    String gameID = "blah";
    Picnic game = GameRepository.getInstance().findGame(gameID);
    if (game == null) {
      // TODO reply with 404
    }
    return game;  // TODO use representation to convert to JSON
  }

/*
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public String startGame(@FormParam("playerID") String playerID) {
    Picnic game = new Picnic();
    Player player = PlayerRepository.getInstance().getPlayer(playerID);
    game.addPlayer(player);
    return GameRepository.getInstance().addGame(game);
  }
*/

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public String spin() {

    // find the right game
    Picnic myGame = GameRepository.getInstance().findGame("test");
    if (myGame == null) {
      myGame = new Picnic();
      GameRepository.getInstance().putGame("test", myGame);
    }

    // call spin
    PicnicSpinner spinner = myGame.getSpinner();
    spinner.spin();
    return spinner.getSelectedValue();
  }
}
