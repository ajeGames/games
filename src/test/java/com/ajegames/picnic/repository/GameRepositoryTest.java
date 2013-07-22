package com.ajegames.picnic.repository;

import com.ajegames.picnic.Picnic;
import com.ajegames.picnic.Player;
import com.ajegames.picnic.service.PicnicSpinnerFactory;
import com.ajegames.picnic.service.SpinnerConfiguration;
import junit.framework.TestCase;

import java.util.List;

public class GameRepositoryTest extends TestCase {

  @Override
  protected void setUp() throws Exception {
    PicnicSpinnerFactory.configureSpinner(new SpinnerConfiguration());
  }

  public void testFindOpenGames() {
    Player player1 = PlayerRepository.createPlayer("Bubba");
    Picnic game1 = GameRepository.createGame();
    Picnic game2 = GameRepository.createGame();
    Picnic game3 = GameRepository.createGame();
    Picnic game4 = GameRepository.createGame();
    game1.addPlayer(player1);
    game4.addPlayer(player1);
    game1.play();
    game4.play();

    List<Picnic> openGames = GameRepository.findOpenGames();
    assertEquals(2, openGames.size());
    assertTrue(openGames.contains(game2));
    assertTrue(openGames.contains(game3));
  }
}
