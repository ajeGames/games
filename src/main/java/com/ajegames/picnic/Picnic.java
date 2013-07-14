package com.ajegames.picnic;

import com.ajegames.picnic.repository.PersistedGameEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * <p>This is the game Picnic.  The objective is to gather a sufficient level of certain picnic items in order to have a
 * successful picnic.  Players must gather at least 3 food items, 2 drink items and 2 utilities.  The first player
 * to do so is the winner.  Special items are throw into the mix.  One kind is a nuisance, which is something that
 * hinders the objective.  Another kind is a prevention, which neutralizes a nuisance.</p>
 *
 * <p>This set of rules lends itself to being completely computer driven.  That is, the game relies entirely on luck.
 * Other versions of the rules may be coded in the future that require additional levels of skill.</p>
 */
public class Picnic implements PersistedGameEntity {

  private static final Logger LOG = LoggerFactory.getLogger(Picnic.class);
  private static final int REQUIRED_FOOD_COUNT = 3;
  private static final int REQUIRED_DRINK_COUNT = 2;
  private static final int REQUIRED_SUPPLY_COUNT = 1;

  String key;
  PicnicGameStatus status;
  PicnicSpinner spinner;
  List<Player> players;
  Map<String, Basket> baskets;
  int indexCurrentPlayer;
  boolean winner;

  public static Picnic createInstance(String key) {
    Picnic out = new Picnic();
    out.initialize(key);
    return out;
  }

  private Picnic() {}

  private void initialize(String key) {
    this.key = key;
    this.status = PicnicGameStatus.STAGING;
    this.spinner = PicnicSpinner.createInstance();
    this.players = Lists.newArrayList();
    this.baskets = Maps.newHashMap();
    indexCurrentPlayer = -1;

  }

  public String getKey() {
    return key;
  }

  public void addPlayer(Player player) {
    LOG.info("Adding player " + player.getName() + ".");
    players.add(player);
    baskets.put(player.getKey(), new Basket());
  }

  public List<Player> getPlayers() {
    return players;
  }

  public boolean isCurrentPlayer(String playerKey) {
    return playerKey.equals(getCurrentPlayer().getKey());
  }

  private Player getCurrentPlayer() {
    return players.get(indexCurrentPlayer);
  }

  private void advanceCurrentPlayer() {
    indexCurrentPlayer = ++indexCurrentPlayer % players.size();
  }

  public PicnicGameStatus getStatus() {
    return status;
  }

  public boolean isPlaying() {
    return status.equals(PicnicGameStatus.PLAYING);
  }

  public boolean isGameOver() {
    return status.equals(PicnicGameStatus.GAME_OVER);
  }

  /**
   * Points to first player and changes game status to "playing..."
   */
  public void play() {
    advanceCurrentPlayer();
    status = PicnicGameStatus.PLAYING;
  }

  public String takeTurn() {
    if (!isPlaying()) {
      LOG.warn("Someone tried to take a turn during staging or after game over.");
      return "";
    }
    Player currentPlayer = players.get(indexCurrentPlayer);
    Basket currentBasket = baskets.get(currentPlayer.getKey());
    Item selectedItem;
    boolean settled;
    do {
      settled = true;  // only exception is when nuisance picked while prevention is held
      selectedItem = spinner.spin();
      LOG.info("Spinner is pointing to " + selectedItem.getDescription());
      if (!selectedItem.isNuisance()) {
        currentBasket.gatherItem(selectedItem);
      } else {
        Nuisance aProblem = (Nuisance) selectedItem;
        if (!currentBasket.hasPrevention(aProblem)) {
          if (aProblem.isAgainstItem()) {
            currentBasket.removeItem(aProblem.getWorksAgainst());
          } else if (aProblem.isAgainstItemType()) {
            currentBasket.removeItemOfType(aProblem.getWorksAgainstType());
          } else if (aProblem.isWipeOut()) {
            currentBasket.empty();
          }
        } else {
          LOG.info("Problem avoided because " + currentPlayer.getName() + " has the prevention for " + aProblem.getValue());
          // TODO make it so player can see that the prevention helped
          settled = false;
        }
      }
    } while (!settled);

    // decide if winner
    if (currentBasket.getFoodCount() >= REQUIRED_FOOD_COUNT
            && currentBasket.getDrinkCount() >= REQUIRED_DRINK_COUNT
            && currentBasket.getSupplyCount() >= REQUIRED_SUPPLY_COUNT) {
      status = PicnicGameStatus.GAME_OVER;
    } else {
      advanceCurrentPlayer();
    }
    return selectedItem.getValue();
  }
}
