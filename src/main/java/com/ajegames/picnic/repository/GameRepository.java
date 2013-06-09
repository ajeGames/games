package com.ajegames.picnic.repository;

import com.ajegames.picnic.Picnic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created for AJE Games by bigdaddy on 5/5/13 at 9:15 PM.
 */
public class GameRepository {

  private static GameRepository instance;
  private long nextID = 0;
  private Map<String, Picnic> games;

  synchronized public static GameRepository getInstance() {
    if (instance == null) {
      instance = new GameRepository();
    }
    return instance;
  }

  private GameRepository() {
    games = new HashMap<String, Picnic>();
  }

  public Picnic findGame(String id) {
    return games.get(id);
  }

  synchronized public String addGame(Picnic gameInstance) {
    String nextIDStr = Long.toString(nextID++);
    games.put(nextIDStr, gameInstance);
    return nextIDStr;
  }

  public String putGame(String id, Picnic gameInstance) {
    games.put(id, gameInstance);
    return id;
  }
}
