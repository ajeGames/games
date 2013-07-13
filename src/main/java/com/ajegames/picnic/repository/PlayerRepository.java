package com.ajegames.picnic.repository;

import com.ajegames.picnic.Picnic;
import com.ajegames.picnic.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created for AJE Games by bigdaddy on 5/5/13 at 9:29 PM.
 */
public class PlayerRepository extends BaseRepository {

  private static PlayerRepository instance;
  private static long nextID = 0;

  private static PlayerRepository getInstance() {
    if (instance == null) {
      instance = new PlayerRepository();
    }
    return instance;
  }

  private Player getPlayer(String key) {
    Object result = getEntities().get(key);
    if (result == null) {
      return null;
    } else {
      return (Player) result;
    }
  }

  private void addPlayer(Player player) {
    this.addEntity(player);
  }

  synchronized public static Player createPlayer(String name) {
    String key = BaseRepository.generateUniqueKey(new String[] { String.valueOf(nextID), name });
    Player player = Player.createPlayer(key, nextID++, name);
    PlayerRepository.getInstance().addPlayer(player);
    return player;
  }

  synchronized public static Player findPlayer(String key) {
    return PlayerRepository.getInstance().getPlayer(key);
  }
}
