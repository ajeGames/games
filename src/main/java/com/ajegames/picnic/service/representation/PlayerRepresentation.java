package com.ajegames.picnic.service.representation;

import com.ajegames.picnic.domain.Player;
import com.ajegames.utility.rest.ResourceLink;

import java.util.Arrays;

public class PlayerRepresentation extends BasePicnicRepresentation {

  private Player player;

  public PlayerRepresentation(Player player, ResourceLink... links) {
    this.player = player;
    this.links = Arrays.asList(links);
  }

  public Player getPlayer() {
    return player;
  }
}
