package com.ajegames.picnic.repository;

import com.ajegames.picnic.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerRepository extends BaseRepository {

  private static final Logger LOG = LoggerFactory.getLogger(PlayerRepository.class);
  private static PlayerRepository instance;

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

  private static String generateUniqueKey() {
    String key;
    do {
      key = KeyGenerator.generateKey(16);
    } while (getInstance().getEntities().containsKey(key));
    LOG.debug("Generated key " + key);
    return key;
  }

  synchronized public static Player createPlayer(String name) {
    String key = PlayerRepository.generateUniqueKey();
    Player player = Player.createPlayer(key, name);
    PlayerRepository.getInstance().addPlayer(player);
    return player;
  }

  synchronized public static Player findPlayer(String key) {
    return PlayerRepository.getInstance().getPlayer(key);
  }
}
