package com.ajegames.dungeons.orm;

import com.ajegames.dungeons.world.PlayerCache;
import com.ajegames.dungeons.world.player.Player;
import org.testng.annotations.Test;

public class WorldPersistenceTest {

  @Test
  public void testStoreWorld() throws Exception {
    Player player = Player.createNewPlayer("Merlin", "human", "magic-user", 12, 18, 16, 13, 9, 11);
    PlayerCache.getInstance().add(player);
    new WorldPersistence().storeWorld();
  }
}
