package com.ajegames.picnic;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created for AJE Games by bigdaddy on 6/22/13 at 5:32 PM.
 */
public class ItemCatalog {

  private static ItemCatalog singleton = new ItemCatalog();

  public static ItemCatalog instance() {
    return singleton;
  }

  private Map<String, Item> spinnerItems = Maps.newHashMap();

  public Item getItem(String key) {
    return spinnerItems.get(key);
  }

  public void addItem(Item picnicItem) {
    spinnerItems.put(picnicItem.getValue(), picnicItem);
  }
}
