package com.ajegames.dungeons.orm;

import com.ajegames.dungeons.world.PlayerCache;
import com.ajegames.dungeons.world.player.Player;
//import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

/**
 * Stores objects in the world for future reference.
 */
public class WorldPersistence {

  public void storeWorld() throws IOException {
//    Gson gson = new Gson();
//    File dataFile = new File("data/world-objects.dat");
//    if (!dataFile.exists() && !dataFile.getParentFile().mkdirs()) {
//      // unable to create file; log an error
//      System.out.println("Unable to open file");
//      return;
//    }
//    Writer out = new FileWriter(dataFile);
//    Collection<Player> players = PlayerCache.getInstance().getPlayers();
//    for (Player player : players) {
//      String toWrite = gson.toJson(player);
//      out.write(toWrite);
//    }
//    out.flush();
//    out.close();
  }

}
