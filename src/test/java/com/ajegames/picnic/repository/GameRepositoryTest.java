package com.ajegames.picnic.repository;

import com.ajegames.picnic.domain.Picnic;
import com.ajegames.picnic.domain.Player;
import com.ajegames.picnic.domain.TestPicnicSpinnerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GameRepositoryTest {

  @BeforeMethod
  protected void init() throws Exception {
    TestPicnicSpinnerFactory.configureSpinnerWithBasicTestItems();
  }

  @Test
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
    assert openGames.size() == 2;
    assert openGames.contains(game2);
    assert openGames.contains(game3);
  }
}
