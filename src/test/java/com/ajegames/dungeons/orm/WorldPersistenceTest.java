package com.ajegames.dungeons.orm;

import com.ajegames.dungeons.world.PlayerCache;
import com.ajegames.dungeons.world.player.Player;
import junit.framework.TestCase;

/**
 */
public class WorldPersistenceTest extends TestCase {

  public void testStoreWorld() {

    Player player = Player.createNewPlayer("Merlin", "human", "magic-user", 12, 18, 16, 13, 9, 11);
    PlayerCache.getInstance().add(player);
    try {
      new WorldPersistence().storeWorld();
    } catch (Exception e) {
      fail("Something bad happened: " + e.getMessage());
    }
  }
}
