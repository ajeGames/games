package com.ajegames.picnic.repository;

import com.ajegames.picnic.Picnic;
import com.ajegames.picnic.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created for AJE Games by bigdaddy on 5/5/13 at 9:29 PM.
 */
public class PlayerRepository {

  private static PlayerRepository instance;
  private long nextID = 0;
  private Map<String, Player> players;

  synchronized public static PlayerRepository getInstance() {
    if (instance == null) {
      instance = new PlayerRepository();
    }
    return instance;
  }

  private PlayerRepository() {
    players = new HashMap<String, Player>();
  }

  public Player getPlayer(String id) {
    return players.get(id);
  }

  synchronized public String addPlayer(Player player) {
    player.setId(nextID);
    String nextIDStr = Long.toString(nextID++);
    players.put(nextIDStr, player);
    return nextIDStr;
  }
}
