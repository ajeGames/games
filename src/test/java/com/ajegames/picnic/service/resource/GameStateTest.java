package com.ajegames.picnic.service.resource;

import com.ajegames.picnic.domain.Picnic;
import com.ajegames.picnic.domain.Player;
import com.ajegames.picnic.domain.TestPicnicSpinnerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by dave on 7/14/13 at 3:57 PM.
 */
public class GameStateTest {

  @BeforeClass
  public void init() {
    TestPicnicSpinnerFactory.configureSpinnerWithBasicTestItems();
  }

  @Test
  public void testBasicGameState() {
    Player a = Player.createPlayer("a", "Albert");
    Player b = Player.createPlayer("b", "Bertie");
    Picnic testGame = Picnic.createInstance("test");
    testGame.addPlayer(a);
    testGame.addPlayer(b);
    GameState state = new GameState(testGame);
    assert state.getPlayers().get(0).getBasket().getFoods().isEmpty();
  }
}
