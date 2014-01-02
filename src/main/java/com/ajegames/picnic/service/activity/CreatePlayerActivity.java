package com.ajegames.picnic.service.activity;

import com.ajegames.picnic.domain.Player;
import com.ajegames.picnic.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatePlayerActivity {
  private static Logger LOG = LoggerFactory.getLogger(CreatePlayerActivity.class);

  public Player create(String name) {
    Player out = PlayerRepository.createPlayer(name);
    LOG.info("Created player with name '" + name + "' (key: " + out.getKey() + ")");
    return out;
  }
}
