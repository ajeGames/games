package com.ajegames.dungeons.world;

import com.ajegames.dungeons.world.player.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * For quick look-up of items in the world.
 */
public class PlayerCache {

  private static PlayerCache singleton;
  private static long nextId = 999;

  public static synchronized PlayerCache getInstance() {
    if (singleton == null) {
      singleton = new PlayerCache();
    }
    return singleton;
  }

  public static long assignNextPlayerId() {
    return nextId++;
  }

  private Map<String, Player> cache;

  private PlayerCache() {
    cache = new HashMap<String, Player>();
  }

  public Player getPlayer(String key) {
    return cache.get(key);
  }

  public void add(Player player) {
    cache.put(Long.toString(assignNextPlayerId()), player);
  }

  public Collection<Player> getPlayers() {
    return cache.values();
  }
}
