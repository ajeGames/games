package com.ajegames.picnic.repository;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created for AJE Games by bigdaddy on 5/26/13 at 8:47 AM.
 */
public class BaseRepository {

  private Map<String, PersistedGameEntity> entities = Maps.newHashMap();

  public void addEntity(PersistedGameEntity entity) {
    entities.put(entity.getKey(), entity);
  }

  protected Map<String, PersistedGameEntity> getEntities() {
    return entities;
  }
}
