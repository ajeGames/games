package com.ajegames.picnic;

import com.ajegames.utility.Spinner;
import com.ajegames.utility.SpinnerOption;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>This is the game Picnic.  The objective is to gather a sufficient level of certain picnic items in order to have a
 * successful picnic.  Players must gather at least 3 food items, 2 drink items and 2 utilities.  The first player
 * to do so is the winner.  Special items are throw into the mix.  One kind is a nuisance, which is something that
 * hinders the objective.  Another kind is a prevention, which neutralizes a nuisance.</p>
 *
 * <p>This set of rules lends itself to being completely computer driven.  That is, the game relies entirely on luck.
 * Other versions of the rules may be coded in the future that require additional levels of skill.</p>
 */
public class Picnic {

  List<Player> players = new ArrayList<Player>();
  int indexCurrentPlayer = -1;
  PicnicSpinner spinner;
  boolean winner;

  private static final int REQUIRED_FOOD_COUNT = 3;
  private static final int REQUIRED_DRINK_COUNT = 2;
  private static final int REQUIRED_UTENSIL_COUNT = 1;

  /**
   * The game can be started and played from the command line.  Ultimately, this will be fronted by a Servlet and
   * exposed as a web app.
   *
   * @param args Player names
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Need to specify the name of at least one player");
    }
    Picnic picnic = new Picnic();
    for (String player : args) {
      picnic.addPlayer(player);
    }

    picnic.play();
  }

  public Picnic() {
    spinner = PicnicSpinner.createPicnicSpinnerWithDefaultOptions();
  }

  public void addPlayer(String playerName) {
    print("Adding player " + playerName + ".");
    players.add(new Player(playerName));
  }

  private void play() {
    // take turn; continue until player wins
    print("\nAnd here we go...");
    print("\n==Play by play==");
    do {
      advanceCurrentPlayer();
      takeTurn();
    } while (!winner());
    print("\nThe winner is " + players.get(indexCurrentPlayer).getName() + "!!!\n");
  }

  public void advanceCurrentPlayer() {
    indexCurrentPlayer = ++indexCurrentPlayer % players.size();
  }

  private boolean winner() {
    return winner;
  }

  private void takeTurn() {
    // spin
    spinner.spin();

    // do something about selection
    Player currentPlayer = players.get(indexCurrentPlayer);
    SpinnerOption selectedItem = spinner.getSelected();

    if (selectedItem instanceof Item) {
      currentPlayer.gatherItem((Item) selectedItem);

    } else if (selectedItem instanceof Nuisance) {
      Nuisance aProblem = (Nuisance) selectedItem;
      if (!currentPlayer.getBasket().hasPrevention(aProblem)) {
        print("==X Do something dastardly to " + currentPlayer.getName() + " due to " + aProblem.getValue());

        if (aProblem.isAgainstItem()) {
          currentPlayer.getBasket().removeItem(aProblem.getWorksAgainst());
        } else if (aProblem.isAgainstItemType()) {
          currentPlayer.getBasket().removeItemOfType(aProblem.getWorksAgainstType());
        } else if (aProblem.isWipeOut()) {
          currentPlayer.getBasket().empty();
        }
      } else {
        print("==O Problem avoided because " + currentPlayer.getName() + " has the prevention for " + aProblem.getValue());
      }
    }
    print("* " + currentPlayer.toString());

    // decide if winner
    if (currentPlayer.getBasket().getFoodCount() >= REQUIRED_FOOD_COUNT
            && currentPlayer.getBasket().getDrinkCount() >= REQUIRED_DRINK_COUNT
            && currentPlayer.getBasket().getUtensilCount() >= REQUIRED_UTENSIL_COUNT) {
      winner = true;
    }
  }

  private void print(String s) {
    System.out.println(s);
  }

}
