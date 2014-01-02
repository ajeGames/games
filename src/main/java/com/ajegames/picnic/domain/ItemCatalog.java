package com.ajegames.picnic.domain;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Holds all of the available picnic items.
 */
public class ItemCatalog {

  private static ItemCatalog instance;

  private Map<String, Item> spinnerItems = Maps.newHashMap();

  synchronized public static ItemCatalog getInstance() {
    if (instance == null) {
      instance = new ItemCatalog();
    }
    return instance;
  }

  public Item getItem(String key) {
    return spinnerItems.get(key);
  }

  public void addItem(Item picnicItem) {
    spinnerItems.put(picnicItem.getValue(), picnicItem);
  }
}
