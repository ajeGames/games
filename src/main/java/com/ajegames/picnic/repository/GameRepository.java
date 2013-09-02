package com.ajegames.picnic.repository;

import com.ajegames.picnic.domain.Picnic;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

public class GameRepository extends BaseRepository {

  private static final Logger LOG = LoggerFactory.getLogger(GameRepository.class);
  private static GameRepository instance;

  private GameRepository() {}

  synchronized public static GameRepository getInstance() {
    if (instance == null) {
      instance = new GameRepository();
    }
    return instance;
  }

  synchronized public static Picnic createGame() {
    String key = GameRepository.generateUniqueKey();
    Picnic game = Picnic.createInstance(key);
    GameRepository.getInstance().addGame(game);
    return game;
  }

  synchronized public static Picnic findGame(String key) {
    return GameRepository.getInstance().getGame(key);
  }

  synchronized public static List<Picnic> findOpenGames() {
    List<Picnic> openGames = Lists.newArrayList();
    Collection<PersistedGameEntity> entities = GameRepository.getInstance().getEntities().values();
    for (PersistedGameEntity entity : entities) {
      if (entity instanceof Picnic) {
        Picnic game = (Picnic) entity;
        if (game.isStaging()) {
          openGames.add(game);
        }
      }
    }
    return openGames;
  }

  private Picnic getGame(String key) {
    Object result = getEntities().get(key);
    if (result == null) {
      return null;
    } else {
      return (Picnic) result;
    }
  }

  private void addGame(Picnic player) {
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
}
