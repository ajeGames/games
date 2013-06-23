package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.*;
import com.ajegames.picnic.repository.GameRepository;
import com.ajegames.picnic.service.SpinnerItemConfig;
import com.ajegames.picnic.service.SpinnerConfiguration;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created for AJE Games by bigdaddy on 5/5/13 at 8:54 PM.
 */
@Path("/picnic/game")
public class GameResource {

  public static PicnicSpinner spinner;

  /*
    TODO: this is flawed; will not work once multiple games share the same spinner;
      could have single configuration that spinner points to
   */

  public GameResource(SpinnerConfiguration spinnerConfig) {
    initializeSpinner(spinnerConfig);
  }

  public void initializeSpinner(SpinnerConfiguration spinnerConfig) {

    // create a new game and stash in repository
    spinner = PicnicSpinner.createEmptySpinner();
    for (SpinnerItemConfig item : spinnerConfig.getFoods()) {
      for (int i = 0; i < item.getWeight(); i++) {
        spinner.addItem(Item.createFood(item.getKey()));
      }
    }
    for (SpinnerItemConfig item : spinnerConfig.getDrinks()) {
      for (int i = 0; i < item.getWeight(); i++) {
        spinner.addItem(Item.createDrink(item.getKey()));
      }
    }
    for (SpinnerItemConfig item : spinnerConfig.getSupplies()) {
      for (int i = 0; i < item.getWeight(); i++) {
        spinner.addItem(Item.createSupply(item.getKey()));
      }
    }
    for (SpinnerItemConfig item : spinnerConfig.getNuisances()) {
      Nuisance toAdd;
      for (int i = 0; i < item.getWeight(); i++) {
        if ("reducesFood".equals(item.getImpact())) {
          toAdd = Nuisance.createAgainstFood(item.getKey());
        } else if ("wipeout".equals(item.getImpact())) {
          toAdd = Nuisance.createWipeOut(item.getKey());
        } else {
          toAdd = Nuisance.create(item.getKey());
        }
        spinner.addNuisance(toAdd);
      }
    }
    for (SpinnerItemConfig item : spinnerConfig.getPreventions()) {
      for (int i = 0; i < item.getWeight(); i++) {
        Item counteracts = ItemCatalog.instance().getItem(item.getCounteracts());
        if (counteracts instanceof Nuisance) {
          spinner.addItem(Prevention.createPrevention(item.getKey(), (Nuisance) counteracts));
        }
      }
    }
  }

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

    // TODO use hash to id games
    Picnic myGame = GameRepository.getInstance().findGame("test");
    if (myGame == null) {
      myGame = new Picnic(spinner);
      GameRepository.getInstance().putGame("test", myGame);
    }
    return myGame.getSpinner().spin().getValue();
  }
}
