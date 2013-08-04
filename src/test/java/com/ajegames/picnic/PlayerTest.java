package com.ajegames.picnic;

import com.ajegames.picnic.repository.PlayerRepository;
import org.testng.annotations.Test;

/**
 * Makes sure that Player behaves as expected.  Player holds picnic items in a basket, so make sure collection and
 * inspection works.
 */
public class PlayerTest {

  @Test
  public void testCreatePlayer() {
    Player player = Player.createPlayer("key", "Tex");
    assert "Tex".equals(player.getName()) : "Expected name to be set";
  }

  @Test
  public void testCreatePlayerUsingRepository() {
    Player bubba = PlayerRepository.createPlayer("Bubba");
    assert bubba.getKey() != null;
    assert bubba.getName() != null;
    assert "Bubba".equals(bubba.getName());
    assert PlayerRepository.findPlayer(bubba.getKey()) != null;
  }
}
