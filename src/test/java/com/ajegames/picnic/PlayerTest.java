package com.ajegames.picnic;

import com.ajegames.picnic.repository.PlayerRepository;
import junit.framework.TestCase;

import java.util.List;

/**
 * Makes sure that Player behaves as expected.  Player holds picnic items in a basket, so make sure collection and
 * inspection works.
 */
public class PlayerTest extends TestCase {

  public void testCreatePlayer() {
    Player player = Player.createPlayer("key", "Tex");
    assertEquals("Expected name to be set", "Tex", player.getName());
  }

  public void testCreatePlayerUsingRepository() {
    Player bubba = PlayerRepository.createPlayer("Bubba");
    assertNotNull(bubba.getKey());
    assertNotNull(bubba.getName());
    assertEquals("Bubba", bubba.getName());
    assertNotNull(PlayerRepository.findPlayer(bubba.getKey()));
  }
}
